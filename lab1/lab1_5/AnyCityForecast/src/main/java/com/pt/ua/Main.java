package com.pt.ua;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import com.pt.ua.WeatherStarter;
import com.pt.ua.IpmaCityForecast;


public class Main 
{
  Timer timer;

  public Main() {
    timer = new Timer();
    timer.scheduleAtFixedRate(new RemindTask(), 0, //initial delay
        20 * 1000); //subsequent rate = 20 sec
  }

  WeatherStarter ws = new WeatherStarter();
  private int[] cityIds = {1010500, 1020500, 1030300, 1030800, 1040200, 1050200, 1060300, 1070500, 1080500, 1081505};
  
  private static Logger logger = LogManager.getLogger(WeatherStarter.class);
  
  class RemindTask extends TimerTask {
    int numRemainingForecasts = 3;
    
    public void run() {
      if (numRemainingForecasts-- > 0) {
        long time = System.currentTimeMillis();
        
        if (time - scheduledExecutionTime() > 5) {
          return;
        }
        
        int randomId = (int)Math.floor(Math.random()*10);
        IpmaCityForecast forecast = ws.getForecast(cityIds[randomId]);
        if (forecast != null) {
          var firstDay = forecast.getData().listIterator().next();

          logger.info( "max temp for the city of ID " + cityIds[randomId] + " for the date of " + 
          firstDay.getForecastDate() + " is "+ Double.parseDouble(firstDay.getTMax()) + " °C");
      } else {
          logger.info( "No results for this request!");
      }

      } else {
        logger.info("Time's up!");
        System.exit(0);
      }
    }
  }

  public static void main(String args[]) {
    new Main();
  }
}
