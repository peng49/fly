package fly.frontend.event;

import fly.frontend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class RegisteredEvent {
    private User user;
}
