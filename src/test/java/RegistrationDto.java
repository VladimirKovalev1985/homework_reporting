import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;


@Data
@AllArgsConstructor
public class RegistrationDto {
    private final String login;
    private final String password;
    private final String status;
}
