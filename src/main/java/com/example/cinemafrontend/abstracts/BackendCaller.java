package com.example.cinemafrontend.abstracts;

import com.example.cinemafrontend.controllers.models.Chair;
import com.example.cinemafrontend.model.Movie;
import com.example.cinemafrontend.model.Staff;
import com.example.cinemafrontend.model.StaffSchedule;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class BackendCaller {

    private static BackendCaller instance = new BackendCaller();

    private HttpURLConnection connection;

    private BackendCaller(){

    }

    public static BackendCaller inst(){
        return instance;
    }

    private String request(String path){
        String output = "";

        try {
            URL url = new URL("http://localhost:8080/" + path);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if(status < 300){
                Scanner scanner = new Scanner(connection.getInputStream());
                while(scanner.hasNextLine()){
                    output += scanner.nextLine();
                }
                scanner.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return output;
    }

    private void post(String path, String body){
        try {
            URL url = new URL("http://localhost:8080/" + path);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            try(OutputStream os = connection.getOutputStream()){
                byte[] input = body.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            try(BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return json object - contains key:
     * schedule_id, date, time, movie_id, salon
     * */
    public JSONArray getSchedulesByMovie(int movieID){
        String data = request("api/v1/schedules/movie/" + movieID);
        return new JSONArray(data);
    }

    public Movie[] getMovies(){
        String data = request("api/v1/movies");
        JSONArray array = new JSONArray(data);
        Movie[] movies = new Movie[array.length()];
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            movies[i] = new Movie(object.getInt("movie_id"),
                    object.getString("title"),
                    object.getString("description"),
                    object.getInt("age_restriction"),
                    object.getString("category_cover_image"),
                    object.getString("cover_image"));
        }
        HashMap<String, String> genres = getGenres();
        for (Movie movie : movies) {
            for (int i : getGenreIdsByMovieId(movie.getMovieId())) {
                movie.addGenre(genres.get(String.valueOf(i)));
            }
        }
        return movies;
    }

    public int[] getGenreIdsByMovieId(int movie_id){
        String data = request("api/v1/movie_genre/"+movie_id);
        return new Gson().fromJson(data, int[].class);
    }

    /**
     * @return key - Integer as string format, value - string
     **/
    public HashMap<String, String> getGenres(){
        String data = request("api/v1/genre");
        return new Gson().fromJson(data, HashMap.class);
    }

    public String[] getTakenChairs(int scheduleID){
        String data = request("api/v1/booked_chairs/taken_chairs/" + scheduleID);
        return new Gson().fromJson(data, String[].class);
    }

    public JSONObject getCustomer(String securityNumber){
        String data = request("api/v1/customers/" + securityNumber);
        return new JSONObject(data);
    }

    public StaffSchedule getStaffTask(int staffId, String date, String time){
        String data = request("api/v1/staff_schedules/" + staffId + "/" + date + "/" + time);
        if(data.equals("")){
            return null;
        }
        JSONObject object = new JSONObject(data);
        return new StaffSchedule(object.getInt("staff_id"),
                object.getString("date"),
                object.getString("time"),
                object.getString("task"));
    }

    public List<Staff> getStaffs(){
        String data = request("api/v1/staffs");
        JSONArray array = new JSONArray(data);
        List<Staff> output = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            output.add(new Staff(object.getInt("staff_id"),
                    object.getString("name"),
                    object.getString("security_number"),
                    object.getString("mail"),
                    object.getString("address"),
                    object.getInt("salary"),
                    object.getString("position")));
        }
        return output;
    }

    public Staff getStaff(int staffId){
        String data = request("api/v1/staffs/" + staffId);
        JSONObject object = new JSONObject(data);
        return new Staff(object.getInt("staff_id"),
                object.getString("name"),
                object.getString("security_number"),
                object.getString("mail"),
                object.getString("address"),
                object.getInt("salary"),
                object.getString("position"));
    }

    public Staff getStaff(String securityNumber){
        String data = request("api/v1/staffs/security_number/" + securityNumber);
        JSONObject object = new JSONObject(data);
        return new Staff(object.getInt("staff_id"),
                object.getString("name"),
                object.getString("security_number"),
                object.getString("mail"),
                object.getString("address"),
                object.getInt("salary"),
                object.getString("position"));
    }

    public List<StaffSchedule> getStaffSchedules(){
        String data = request("api/v1/staff_schedules");
        JSONArray array = new JSONArray(data);
        List<StaffSchedule> output = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject object = array.getJSONObject(i);
            output.add(new StaffSchedule(object.getInt("staff_id"),
                    object.getString("date"),
                    object.getString("time"),
                    object.getString("task")));
        }
        return output;
    }

    public void createTask(int staffId, String date, String time, String task){
        JSONObject object = new JSONObject();
        object.put("staff_id", staffId);
        object.put("date", date);
        object.put("time", time);
        object.put("task", task);
        System.out.println(object.toString());
        post("api/v1/staff_schedules", object.toString());
    }

    public JSONArray getTickets(int customerId) {
        String data = request("api/v1/bookings/" + customerId);
        JSONArray array = new JSONArray(data);
        for (int i = 0; i < array.length(); i++) {
            JSONObject ticketObject = array.getJSONObject(i);
            //chairs
            String bookedChairsData = request("api/v1/booked_chairs/booked_chairs/" + ticketObject.getInt("booking_id"));
            List<String> bookedChairs = new ArrayList<>();
            JSONArray bookedChairsArray = new JSONArray(bookedChairsData);
            for (int j = 0; j < bookedChairsArray.length(); j++) {
                bookedChairs.add(bookedChairsArray.getJSONObject(j).getInt("chair_number")+"");
            }
            ticketObject.put("chairs", String.join(",", bookedChairs));
            //schedule
            String scheduleData = request("api/v1/schedules/" + array.getJSONObject(i).getInt("schedule_id"));
            JSONObject scheduleObject = new JSONObject(scheduleData);
            ticketObject.put("date", scheduleObject.getString("date"));
            ticketObject.put("time", scheduleObject.getString("time"));
            ticketObject.put("salon_id", scheduleObject.getInt("salon_id"));
            //movie
            String movieData = request("api/v1/movies/" + scheduleObject.getInt("movie_id"));
            JSONObject movieObject = new JSONObject(movieData);
            ticketObject.put("title", movieObject.getString("title"));
            ticketObject.put("image", movieObject.getString("category_cover_image"));
            //
        }
        return array;
    }

    /**
     * @param chairs ex: 1,2,3,4 where the numbers correspond to chair id.
     * */
    public void addTicket(int customerID, int amountOfAdults, int amountOfChildren, int amountOfSeniors, int amountOfStudents, int scheduleID, List<Chair> chairs){
        JSONObject body = new JSONObject();
        body.put("customer_id", customerID);
        body.put("amount_of_adults", amountOfAdults);
        body.put("amount_of_children", amountOfChildren);
        body.put("amount_of_seniors", amountOfSeniors);
        body.put("amount_of_students", amountOfStudents);
        body.put("schedule_id", scheduleID);
        body.put("chair_numbers", String.join(",", chairs.stream().map(c -> String.valueOf(c.getID())).toList()));
        post("api/v1/bookings", body.toString());
    }

    public void deleteTicket(int bookingId){
        String data = request("api/v1/bookings/delete/" + bookingId);
        System.out.println(data);
    }

    public boolean isSecurityNumberAvailable(String securityNumber){
        String data = request("api/v1/customers/isSecurityNumberAvailable/" + securityNumber);
        return Boolean.valueOf(data);
    }

    public void createCustomer(String name, String securityNumber, String mail){
        JSONObject body = new JSONObject();
        body.put("name", name);
        body.put("security_number", securityNumber);
        body.put("mail", mail);
        post("api/v1/customers", body.toString());
    }

}
