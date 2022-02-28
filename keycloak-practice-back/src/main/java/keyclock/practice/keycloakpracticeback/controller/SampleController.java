package keyclock.practice.keycloakpracticeback.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import keyclock.practice.keycloakpracticeback.security.CustomPrincipal;

@CrossOrigin
@RestController
public class SampleController {

    @GetMapping("/user/hello")
    public String userHello() {
        return "Any user can call this api.";
    }

    @GetMapping("/admin/hello")
    public String adminHello(@AuthenticationPrincipal CustomPrincipal principal) {
        return "HELLO " + principal.getName();
    }
}
