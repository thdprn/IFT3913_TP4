package ua.karatnyk;

import static org.junit.Assert.*;
import java.text.ParseException;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import ua.karatnyk.impl.CurrencyConversion;
import ua.karatnyk.impl.CurrencyConvertor;
import ua.karatnyk.impl.ExpensesProgramAPI;
import ua.karatnyk.impl.JsonWorker;
import ua.karatnyk.impl.OfflineJsonWorker;


public class CurrencyConvertorTest {

	private CurrencyConversion conversion;
    private Map<String, Double> conversionRates;

	
	@Before
	public void init() {
        conversion = new CurrencyConversion();
        conversionRates = conversion.getRates();
	}

    // Tests boite noire
    
    //Partition du domaine
    @Test
    public void testUSDCADNegative() {
        try {
            CurrencyConvertor.convert(-1, "USD", "CAD", conversion);
            fail("Le montant n'est pas entre '0' et '10000' et la fonction n'a pas crash");
        }
        catch(Exception e) {
            //TODO
        }
    }
    @Test
    public void testUSDCADOverLimit() {
        try {
            CurrencyConvertor.convert(10001, "USD", "CAD", conversion);
            fail("Le montant n'est pas entre '0' et '10000' et la fonction n'a pas crash");
        }
        catch(Exception e) {
            
        }
    }
    @Test
    public void testUSDCADLowerThreshold() {
        try {
            CurrencyConvertor.convert(0, "USD", "CAD", conversion);
            
        }
        catch(Exception e) {
            fail("Exception occured. '0' inputed and function crashed");
        }
    }
    @Test
    public void testUSDCADUpperThreshold() {
        try {
            CurrencyConvertor.convert(10000, "USD", "CAD", conversion);
            
        }
        catch(Exception e) {
            fail("Exception occured. '10000' inputed and function crashed");
        }
    }
    @Test
    public void testUSDCADInside() {
        try {
            CurrencyConvertor.convert(5000, "USD", "CAD", conversion);
            
        }
        catch(Exception e) {
            fail("Exception occured. '5000' inputed and function crashed");
        }
    }

    //Verification conversions
    @Test
    public void testUSDCAD() {
        try {
            String currency1 = "USD";
            String currency2 = "CAD";

            double amount = 376.0;
            double amountConverted = CurrencyConvertor.convert(5000, "USD", "CAD", conversion);
            
            Bool passed = amount/rates

        }
        catch(Exception e) {
            fail("Exception occured. '5000' inputed and function crashed");
        }
    }

    // Tests boite blanche

    
}
