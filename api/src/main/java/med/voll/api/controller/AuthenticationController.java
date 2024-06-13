package med.voll.api.controller;


import jakarta.validation.Valid;
import med.voll.api.domain.user.User;
import med.voll.api.domain.user.UserAuthenticationDTO;
import med.voll.api.infra.security.JWTTokenDTO;
import med.voll.api.infra.security.TokenService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity Authenticate(@RequestBody @Valid UserAuthenticationDTO user) {
        Authentication authToken=new UsernamePasswordAuthenticationToken(user.email(),user.password());
        var authenticatedUser=authenticationManager.authenticate(authToken);

        var JWTtoken=tokenService.generateToken((User) authenticatedUser.getPrincipal());

    return ResponseEntity.ok(new JWTTokenDTO(JWTtoken));
    }


}
