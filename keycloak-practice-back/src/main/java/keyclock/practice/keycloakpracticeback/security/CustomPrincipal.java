package keyclock.practice.keycloakpracticeback.security;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CustomPrincipal implements Serializable {
    private String id;
    private String name;
}
