package dbwls.cloudProject.common.security;

import dbwls.cloudProject.common.entity.JwtResponse;
import dbwls.cloudProject.common.entity.TestObject;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class JwtAuthenticationResourceApiController {

    private final JwtEncoder jwtEncoder;

    @GetMapping("/test-api/{username}")
    @PreAuthorize("hasRole('USER') and #username == authentication.name")
    @PostAuthorize("returnObject.message == 'success!!yujin'")
    public TestObject testApiCall(@PathVariable String username) {
        return new TestObject("success!!" + username);
    }

    @PostMapping("/authenticate")
    @PermitAll
    public JwtResponse authenticate(Authentication authentication) {
        return new JwtResponse(createToken(authentication));
    }

    private String createToken(Authentication authentication) {
        return jwtEncoder.encode(createJwtEncoderParams(authentication)).getTokenValue();
    }

    private JwtEncoderParameters createJwtEncoderParams(Authentication authentication) {
        return JwtEncoderParameters.from(JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(30*60))
                .subject(authentication.getName())
                .claim("scope", createClaim(authentication))
                .build()
        );
    }

    private String createClaim(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
    }
}
