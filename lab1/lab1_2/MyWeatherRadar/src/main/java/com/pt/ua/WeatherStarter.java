package com.pt.ua;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * demonstrates the use of the IPMA API for weather forecast
 */
public class WeatherStarter {

    //todo: should generalize for a city passed as argument
    private static int cityId = 1010500;

    // Logger
    private static Logger logger = LogManager.getLogger(WeatherStarter.class);

    public static void  main(String[] args ) {

        // get a retrofit instance, loaded with the GSon lib to convert JSON into objects
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ipma.pt/open-data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // create a typed interface to use the remote API (a client)
        IpmaService service = retrofit.create(IpmaService.class);
        // prepare the call to remote endpoint
        Call<IpmaCityForecast> callSync;
        
        if(args.length > 0) {
            cityId = Integer.parseInt(args[0]);
        }
        
        callSync = service.getForecastForACity(cityId);
        logger.info(callSync);

        try {
            Response<IpmaCityForecast> apiResponse = callSync.execute();
            logger.info(apiResponse);
            IpmaCityForecast forecast = apiResponse.body();
            logger.info(forecast);

            if (forecast != null) {
                var firstDay = forecast.getData().listIterator().next();

                System.out.printf( "max temp for the city of ID %d for the date of %s is %4.1f %n °C",
                        cityId,
                        firstDay.getForecastDate(),
                        Double.parseDouble(firstDay.getTMax()));
            } else {
                System.out.println( "No results for this request!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
