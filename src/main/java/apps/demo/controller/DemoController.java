package apps.demo.controller;

import apps.demo.filemanagement.ReadFile;
import apps.demo.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import static apps.demo.dbmanagement.worksDB.insertUser;
import static apps.demo.dbmanagement.worksDB.selectUserByLogin;
import static apps.demo.filemanagement.WriteFile.writeFile;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class DemoController {
    @GetMapping("/getData")
    ResponseEntity<?> getJson(@RequestParam(value = "login") String login) {
        User user = selectUserByLogin(login);
        if (user == null) {
            return new ResponseEntity<>(new SQLException(), HttpStatusCode.valueOf(500));
        }
        writeFile(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/getRandomStringFromFile")
    public ResponseEntity<?> getRandomStringFromFile() {
        String randomLine = ReadFile.readFile();
        return new ResponseEntity<>(randomLine, HttpStatus.OK);
    }

    @PostMapping("/postData")
    ResponseEntity<?> postJson(@RequestBody User user) {

        LocalDate date = LocalDate.now();
        user.setDate(Date.valueOf(date));
        int rowCount = insertUser(user);

        if (user.getLogin().isEmpty() || user.getPassword().isEmpty()) {
            return new ResponseEntity<>(new SQLException(), HttpStatusCode.valueOf(400));
        }

        return new ResponseEntity<>(rowCount, HttpStatus.OK);
    }
}
