package com.zambezia.mathplusplus;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.zambezia.mathplusplus.Expression.ExpressionEval;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ExampleUnitTest.class , Log.class})

public class ExampleUnitTest {
    ExpressionEval expEval = new ExpressionEval();

    public List<String> stringToListString(String str)
    {
        char[] chArray = str.toCharArray();
        List<String> expList = new ArrayList<String>();
        for(int i = 0; i<chArray.length; i++)
        {
            expList.add(Character.toString(chArray[i]));
        }
        return expList;
    }

    //eval 2sin60 should throw exception
    @Test
    public void eval_2sin60() throws Exception {
        PowerMockito.mockStatic(Log.class);
       boolean er= false;
        try {
            String str = "2sin60";
            double res = expEval.solve(stringToListString(str));
        }
        catch(Exception exp)
        {
            er = true;
        }
        assertEquals(true, er);
    }

    @Test
    public void eval_square() throws Exception {
        PowerMockito.mockStatic(Log.class);
        double res = -1;
        boolean er= false;
        try {
            String str = "2²+2²";
            res = expEval.solve(stringToListString(str));
        }
        catch(Exception exp)
        {
            er = true;
        }
        assertEquals(8.0, res);
    }
}