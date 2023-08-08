package org.junit.jupiter.api.smells;

import org.apiguardian.api.API;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.*;

import static org.apiguardian.api.API.Status.EXPERIMENTAL;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(NoAssertionExtension.class)
@API(status = EXPERIMENTAL, since = "5.0")
public @interface NoAssertion {
}
