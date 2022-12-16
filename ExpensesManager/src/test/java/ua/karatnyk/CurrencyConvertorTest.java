package ua.karatnyk;

import static org.junit.Assert.*;

import java.io.Console;
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

	private static OfflineJsonWorker offlineJson;
    private CurrencyConversion conversion;
    private Map<String, Double> conversionRates;

    
    @Before
    public void init() {
        offlineJson = new OfflineJsonWorker();
        conversion = offlineJson.parser();;
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
            double x = CurrencyConvertor.convert(0, "USD", "CAD", conversion);
            System.out.println(x);;
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
    public void testConversionUSDCAD() {
        try {
            double amount = 3576;
            String currency1 = "USD";
            String currency2 = "CAD";

            double amountConverted = CurrencyConvertor.convert(amount, currency1, currency2, conversion);
            
            double amountCalculated = amount/conversionRates.get(currency1)*conversionRates.get(currency2);

            assertTrue("Message" , amountConverted == amountCalculated);
        }
        catch(Exception e) {
            fail("Exception occured. TODO");
        }
    }
    @Test
    public void testConversionCADGBP(){
        try {
            double amount = 103;
            String currency1 = "CAD";
            String currency2 = "GBP";

            double amountConverted = CurrencyConvertor.convert(amount, currency1, currency2, conversion);
            
            double amountCalculated = amount/conversionRates.get(currency1)*conversionRates.get(currency2);

            assertTrue("Message" , amountConverted == amountCalculated);
        }
        catch(Exception e) {
            fail("Exception occured. TODO");
        }
    }
    @Test
    public void testConversionGBPEUR(){
        try {
            double amount = 9853;
            String currency1 = "GBP";
            String currency2 = "EUR";

            double amountConverted = CurrencyConvertor.convert(amount, currency1, currency2, conversion);
            
            double amountCalculated = amount/conversionRates.get(currency1)*conversionRates.get(currency2);

            assertTrue("Message" , amountConverted == amountCalculated);
        }
        catch(Exception e) {
            fail("Exception occured. TODO");
        }
    }
    @Test
    public void testConversionEURCHF(){
        try {
            double amount = 503.3;
            String currency1 = "EUR";
            String currency2 = "CHF";

            double amountConverted = CurrencyConvertor.convert(amount, currency1, currency2, conversion);
            
            double amountCalculated = amount/conversionRates.get(currency1)*conversionRates.get(currency2);

            assertTrue("Message" , amountConverted == amountCalculated);
        }
        catch(Exception e) {
            fail("Exception occured. TODO");
        }
    }
    @Test
    public void testConversionCHFINR(){
        try {
            double amount = 24.9;
            String currency1 = "CHF";
            String currency2 = "INR";

            double amountConverted = CurrencyConvertor.convert(amount, currency1, currency2, conversion);
            
            double amountCalculated = amount/conversionRates.get(currency1)*conversionRates.get(currency2);

            assertTrue("Message" , amountConverted == amountCalculated);
        }
        catch(Exception e) {
            fail("Exception occured. TODO");
        }
    }
    @Test
    public void testConversionINRAUD(){
        try {
            double amount = 5680;
            String currency1 = "INR";
            String currency2 = "AUD";

            double amountConverted = CurrencyConvertor.convert(amount, currency1, currency2, conversion);
            
            double amountCalculated = amount/conversionRates.get(currency1)*conversionRates.get(currency2);

            assertTrue("Message" , amountConverted == amountCalculated);
        }
        catch(Exception e) {
            fail("Exception occured. TODO");
        }
    }
    
    // Tests boite blanche

    
}