package apps.demo.controller;

import apps.demo.models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import static apps.demo.dataBase.worksDB.insertUser;
import static apps.demo.dataBase.worksDB.selectUserByLogin;

@RestController
public class DemoController {
    @GetMapping("/getData")
    ResponseEntity<?> getJson() {
        String login = "user1";
        User user = selectUserByLogin(login);
        if (user == null) {
            return new ResponseEntity<>(new SQLException(), HttpStatusCode.valueOf(500));
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/postData")
    ResponseEntity<?> postJson(@RequestBody User user) {

        LocalDate date = LocalDate.now();
        user.setDate(Date.valueOf(date));
        user.setEmail(user.getLogin() + "@mail.ru");
        int rowCount = insertUser(user);

        if (user.getLogin().isEmpty() || user.getPassword().isEmpty()) {
            return new ResponseEntity<>(new SQLException(), HttpStatusCode.valueOf(400));
        }

        return new ResponseEntity<>(rowCount, HttpStatus.OK);
    }
}
