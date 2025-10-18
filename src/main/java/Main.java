import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        System.out.println("PasswordEncoder ============");
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encoded = encoder.encode("pass");
//        boolean pass = encoder.matches("pass", encoded);
        System.out.println(encoded);

        System.out.println("BCryptPasswordEncoder ============");
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encoded2 = bCryptPasswordEncoder.encode("pass");
        System.out.println(encoded2);


        System.out.println("DelegatingPasswordEncoder ============");
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("argon2", new Argon2PasswordEncoder(3, 5, 8, 13, 21)); // fibo3

        DelegatingPasswordEncoder delegating = new DelegatingPasswordEncoder("bcrypt", encoders);
        System.out.println(delegating.encode("pass"));

        System.out.println("ARGO2 ============");
        PasswordEncoder argo = new Argon2PasswordEncoder(
                16,
                32,
                1,
                65536,
                3
        );
        System.out.println(argo.encode("pass"));
    }
}
