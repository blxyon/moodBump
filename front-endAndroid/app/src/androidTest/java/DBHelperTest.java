import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;

import backend.DBHelper;

public class DBHelperTest {
    @Test
    public void testDB() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DBHelper db = new DBHelper(appContext);
    }
}