package fly.frontend.event;

import fly.frontend.entity.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class RegisteredEvent {
    private User user;
}
