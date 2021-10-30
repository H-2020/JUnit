package be.intec.les111.repositories;

import be.intec.les111.models.entities.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {


    @Test
    void createSuccess() {
        UserRepository repository = new UserRepository();

        List<UserEntity> searchResults = repository.searchByEmail("yilmaz@mail.be");
        boolean empty = searchResults.isEmpty();

        if (empty) {
            UserEntity user = new UserEntity();
            user.setEmail("new@mail.be");
            user.setPasscode("1234");

            int expected = 1;

            int actual = repository.create(user);

            Assertions.assertEquals(expected, actual);
        }
    }

    @Test
    void read() {

        UserRepository repository = new UserRepository();

        List<UserEntity> expected = new ArrayList<>();


        UserEntity user1 = new UserEntity();
        user1.setEmail("marie@mail.be");
        user1.setPasscode("1234");

        UserEntity user2 = new UserEntity();
        user2.setEmail("elvis@mail.be");
        user2.setPasscode("1234");

        repository.create(user1);
       repository.create(user2);


        expected.add(user1);

        List<UserEntity> actual = repository.read();

        Assertions.assertArrayEquals(expected.toArray(), actual.toArray());

    }

    void testRead() {
    }

    void testRead1() {
    }

    void update() {
    }

    void delete() {
        UserRepository repository = new UserRepository();
        int expected=


        int actual = repository.delete(1);

        Assertions.assertEquals(expected, actual);




    }

    void updateEmail() {
    }

    void updatePasscode() {
    }

    void searchByEmail() {
    }

    void searchByPasscode() {
    }
}