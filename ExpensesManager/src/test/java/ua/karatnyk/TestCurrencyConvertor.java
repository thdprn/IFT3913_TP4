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


public class TestCurrencyConvertor {

	private static OfflineJsonWorker offlineJsonWorker;
    private CurrencyConversion conversion;
    private Map<String, Double> conversionRates;

    
    @Before
    public void init() {
        offlineJsonWorker = new OfflineJsonWorker();
        conversion = offlineJsonWorker.parser();;
        conversionRates = conversion.getRates();
    }


    // BOITE NOIRE
    
    //#region Partition du domaine
    @Test
    public void testLowerThreshold() {
        try {
            double x = CurrencyConvertor.convert(0, "USD", "CAD", conversion);
            System.out.println(x);;
        }
        catch(Exception e) {
            fail("Exception occured. '0' inputed and function crashed");
        }
    }
    @Test
    public void testUpperThreshold() {
        try {
            CurrencyConvertor.convert(10000, "USD", "CAD", conversion);
            
        }
        catch(Exception e) {
            fail("Exception occured. '10000' inputed and function crashed");
        }
    }
    @Test
    public void testInsideInterval() {
        try {
            CurrencyConvertor.convert(5000, "USD", "CAD", conversion);
            
        }
        catch(Exception e) {
            fail("Exception occured. '5000' inputed and function crashed");
        }
    }
    @Test
    public void testUnderLimit() {
        try {
            CurrencyConvertor.convert(-1, "USD", "CAD", conversion);
            fail("Le montant est inférieur à '0' et la fonction n'a pas crash");
        }
        catch(Exception e) {
            //TODO
        }
    }
    @Test
    public void testOverLimit() {
        try {
            CurrencyConvertor.convert(10001, "USD", "CAD", conversion);
            fail("Le montant est supérieur à '10000' et la fonction n'a pas crash");
        }
        catch(Exception e) {
            
        }
    }
    //#endregion

    //#region Verification conversions
    @Test
    public void testConversionUSDCAD() {
        try {
            double amount = 3576;
            String currency1 = "USD";
            String currency2 = "CAD";

            double amountConverted = CurrencyConvertor.convert(amount, currency1, currency2, conversion);
            
            double amountCalculated = amount/conversionRates.get(currency1)*conversionRates.get(currency2);

            assertTrue("Conversion failed, incorrect output", amountConverted == amountCalculated);
        }
        catch(Exception e) {
            fail("Exception occured. Inputs should be accepted but function crashed");
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

            assertTrue("Conversion failed, incorrect output" , amountConverted == amountCalculated);
        }
        catch(Exception e) {
            fail("Exception occured. Inputs should be accepted but function crashed");
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

            assertTrue("Conversion failed, incorrect output" , amountConverted == amountCalculated);
        }
        catch(Exception e) {
            fail("Exception occured. Inputs should be accepted but function crashed");
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

            assertTrue("Conversion failed, incorrect output" , amountConverted == amountCalculated);
        }
        catch(Exception e) {
            fail("Exception occured. Inputs should be accepted but function crashed");
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

            assertTrue("Conversion failed, incorrect output" , amountConverted == amountCalculated);
        }
        catch(Exception e) {
            fail("Exception occured. Inputs should be accepted but function crashed");
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

            assertTrue("Conversion failed, incorrect output" , amountConverted == amountCalculated);
        }
        catch(Exception e) {
            fail("Exception occured. Inputs should be accepted but function crashed");
        }
    }
    @Test
    public void testConversionToEmpty(){
        try {
            double amount = 5680;
            String currency1 = "USD";
            String currency2 = "";

            CurrencyConvertor.convert(amount, currency1, currency2, conversion);
            fail("Exception occured. Empty string inputed and conversion did not crash");
        }
        catch(Exception e) {
        }
    }
    @Test
    public void testConversionFromEmpty(){
        try {
            double amount = 5680;
            String currency1 = "";
            String currency2 = "CAD";

            CurrencyConvertor.convert(amount, currency1, currency2, conversion);
            fail("Exception occured. Empty string inputed and conversion did not crash");
        }
        catch(Exception e) {
        }
    }
    @Test
    public void testConversionUSDMXN(){
        try {
            double amount = 5680;
            String currency1 = "USD";
            String currency2 = "MXN";

            CurrencyConvertor.convert(amount, currency1, currency2, conversion);
            fail("Exception occured. 'MXN' inputed as 'to' and not accepted currency. Function did not crash");
        }
        catch(Exception e) {
        }
    }
    @Test
    public void testConversionBOBUSD(){
        try {
            double amount = 5680;
            String currency1 = "BOB";
            String currency2 = "USD";

            CurrencyConvertor.convert(amount, currency1, currency2, conversion);
            fail("Exception occured. 'BOB' inputed as 'from' and not accepted currency. Function did not crash");
        }
        catch(Exception e) {
        }
    }
    @Test
    public void testConversionETHUSD(){
        try {
            double amount = 5680;
            String currency1 = "ETH";
            String currency2 = "USD";

            CurrencyConvertor.convert(amount, currency1, currency2, conversion);
            fail("Exception occured. 'ETH' not in currency list and function did not crash");
        }
        catch(Exception e) {
        }
    }
    //#endregion
    


    // BOITE BLANCHE
    
    //#region Couverture des intructions
    @Test
    public void testEnterElse(){
        try {
            double amount = 9999;
            String currency1 = "CAD";
            String currency2 = "USD";

            CurrencyConvertor.convert(amount, currency1, currency2, conversion);
        }
        catch(Exception e) {
            fail("Exception occured. Currencies accepted but function crashed");
        }
    }
    @Test
    public void testEnterIf(){
        try {
            double amount = 20;
            String currency1 = "";
            String currency2 = "";

            CurrencyConvertor.convert(amount, currency1, currency2, conversion);
            fail("Exception occured. '' not in currency list and function did not crash");
        }
        catch(Exception e) {
        }
    }
    //#endregion

    //#region Critère de couverture des conditions (et des arcs)
    @Test
    public void testEnterIfFirstWrong(){
        try {
            double amount = 20;
            String currency1 = "DOGE";
            String currency2 = "USD";

            CurrencyConvertor.convert(amount, currency1, currency2, conversion);
            fail("Exception occured. 'DOGE' not in currency list and function did not crash");
        }
        catch(Exception e) {
        }
    }
    @Test
    public void testEnterIfSecondWrong(){
        try {
            double amount = 20;
            String currency1 = "CAD";
            String currency2 = "SOL";

            CurrencyConvertor.convert(amount, currency1, currency2, conversion);
            fail("Exception occured. 'SOL' not in currency list and function did not crash");
        }
        catch(Exception e) {
        }
    }
    @Test
    public void testEnterIfBothWrong(){
        try {
            double amount = 20;
            String currency1 = "ADA";
            String currency2 = "MATIC";

            CurrencyConvertor.convert(amount, currency1, currency2, conversion);
            fail("Exception occured. 'ADA' & 'MATIC' not in currency list and function did not crash");
        }
        catch(Exception e) {
        }
    }
    //#endregion

}