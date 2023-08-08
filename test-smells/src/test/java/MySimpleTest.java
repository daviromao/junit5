import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.smells.NoAssertion;
import org.junit.jupiter.api.Assertions;

@NoAssertion
public class MySimpleTest {
    @Test
    public void sample(){
        Assertions.assertEquals(5, 5);
    }

    @Test
    public void sample_without_assert(){
        int t = 5;
    }

    @Test
    @EnabledIf("alwaysFalse")
    public void sample_enabled_if(){
        Assertions.assertEquals(3, 3);
    }

    public boolean alwaysFalse(){
        return false;
    }
}
