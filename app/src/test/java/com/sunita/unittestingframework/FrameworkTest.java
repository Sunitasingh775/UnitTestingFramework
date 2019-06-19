package com.sunita.unittestingframework;

import android.content.Context;

import com.sunita.unittestingframework.framework.CSVFileData;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class FrameworkTest {git

    @Mock
    Context context;

    private List<String> data = new ArrayList<>();

    @Before
    public void setup() throws Exception {

        InputStream inputStream = context.getResources().openRawResource(R.raw.stats);

        CSVFileData csvFileData = new CSVFileData(inputStream);
        List<String[]> scoreList = csvFileData.read();

        for (String[] scoreData : scoreList) {
            data.addAll(Arrays.asList(scoreData));
        }
    }

    @Test
    public void nameValidator_CorrectName_ReturnsTrue() {
        assertTrue(Helpers.validateUserName(data.get(0)));
    }

    @Test
    public void nameValidator_EmptyString_ReturnsFalse() {
        assertFalse(Helpers.validateUserName(data.get(1)));
    }

    @Test
    public void nameValidator_ShortLengthName_ReturnsFalse() {
        assertFalse(Helpers.validateUserName(data.get(2)));
    }

    @Test
    public void nameValidator_LengthExceededName_ReturnsFalse() {
        assertFalse(Helpers.validateUserName(data.get(3)));
    }

    @Test
    public void emailValidator_CorrectEmail_ReturnTrue() {
        assertTrue(EmailValidator.isValidEmail(data.get(4)));
    }

    @Test
    public void emailValidator_CorrectEmailSubDomain_ReturnsTrue() {
        assertTrue(EmailValidator.isValidEmail(data.get(5)));
    }

    @Test
    public void emailValidator_InvalidEmailNoTld_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail(data.get(6)));
    }

    @Test
    public void emailValidator_InvalidEmailDoubleDot_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail(data.get(7)));
    }

    @Test
    public void emailValidator_InvalidEmailNoUsername_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail(data.get(8)));
    }

    @Test
    public void emailValidator_EmptyString_ReturnsFalse() {
        assertFalse(EmailValidator.isValidEmail(data.get(9)));
    }
}
