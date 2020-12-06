import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Stream;


public class RouteCalculatorTest extends TestCase {
    List<Station> route;
    StationIndex stationIndexTest;

    List<Station> routeBuffer;
    List<Station> routeOnOneLine;
    List<Station> routeWithOneTransfer;
    List<Station> routeWithTwoTransfers;


    RouteCalculator routeCalculator;


    @Override
    public void setUp() throws Exception {

         /* Общая структура:
        Три ветки метро по шесть станций.

        Первая_1_1
        Вторая_1_2                                     Первая_2_1                                  Первая_3_1
        Третья_1_3                                     Вторая_2_2 ======переход на третью ветку====Вторая_3_2
        Четвертая_1_4                                  Третья_2_3                                  Третья_3_3
        Пятая_1_5                                      Четвертая_2_4                               Четвертая_3_4
        Шестая_1_6   =======переход на вторую ветку=== Пятая_2_5                                   Пятая_3_5
                                                       Шестая_2_6                                  Шестая_3_5
        */



        route = new ArrayList<>();
        stationIndexTest = new StationIndex();


      //  Линии
        Line line1 = new Line(1, "Первая_1");
        Line line2 = new Line(2, "Вторая_2");
        Line line3 = new Line(3, "Третья_3");

        // Станции


        Station one1 = new Station("Первая_1_1", line1);
        Station two1 = new Station("Вторая_1_2", line1);
        Station three1 = new Station("Третья_1_3", line1);
        Station four1 = new Station("Четвертая_1_4", line1);
        Station five1 = new Station("Пятая_1_5", line1);
        Station six1 = new Station("Шестая_1_6", line1);

        Station one2 = new Station("Первая_2_1", line2);
        Station two2 = new Station("Вторая_2_2", line2);
        Station three2 = new Station("Третья_2_3", line2);
        Station four2 = new Station("Четвертая_2_4", line2);
        Station five2 = new Station("Пятая_2_5", line2);
        Station six2 = new Station("Шестая_2_6", line2);

        Station one3 = new Station("Первая_3_1", line3);
        Station two3 = new Station("Вторая_3_2", line3);
        Station three3 = new Station("Третья_3_3", line3);
        Station four3 = new Station("Четвертая_3_4", line3);
        Station five3 = new Station("Пятая_3_5", line3);
        Station six3 = new Station("Шестая_3_6", line3);

        // записываю станции в линию

        Stream.of(one1,two1,three1,four1,five1,six1).forEach(line1::addStation);
        Stream.of(one2,two2,three2,four2,five2,six2).forEach(line2::addStation);
        Stream.of(one3,two3,three3,four3,five3,six3).forEach(line3::addStation);

        Stream.of(line1, line2, line3).forEach(stationIndexTest::addLine);
        Stream.of(one1,two1,three1,five1,four1,six1,one2,two2,three2,four2,five2,six2,one3,two3,three3,four3,five3,six3).
                forEach(stationIndexTest::addStation);


        // добавляем пересадки в метро

        stationIndexTest.addConnection(new ArrayList<>(Arrays.asList(six1, five2)));
        stationIndexTest.addConnection(new ArrayList<>(Arrays.asList(two2, two3)));
        routeCalculator = new RouteCalculator(stationIndexTest);


        //маршруты для тестирования расчета времени движения

        route.add(five2);
        route.add(six1);
        route.add(five1);
        route.add(four1);


    //    создаю тестовые маршруты

        routeOnOneLine = new ArrayList<>();
        routeOnOneLine.addAll(getList("Первая_1_1", "Вторая_1_2", "Третья_1_3", "Четвертая_1_4"));
        routeWithOneTransfer = new ArrayList<>();
        routeWithOneTransfer.addAll(getList("Первая_2_1","Вторая_2_2", "Вторая_3_2", "Третья_3_3"));
        routeWithTwoTransfers = new ArrayList<>();
        routeWithTwoTransfers.addAll(getList("Пятая_1_5","Шестая_1_6","Пятая_2_5","Четвертая_2_4","Третья_2_3", "Вторая_2_2","Вторая_3_2","Третья_3_3"));

}

    public List<Station> getList(String... names) {
        routeBuffer = new ArrayList<>();
        for (String name: names) {
            routeBuffer.add(stationIndexTest.getStation(name));
        }
        return  routeBuffer;
    }


    @Test
    public void testCalculateDuration()
    {
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 8.5;
        assertEquals(expected,actual);

    }

    @Test
    public void testGetShortestRoute ()
    {



        // тестирование без пересадок
        List<Station> actual = routeCalculator.getShortestRoute(stationIndexTest.getStation("Первая_1_1"), stationIndexTest.getStation("Четвертая_1_4"));
        assertEquals(routeOnOneLine, actual);


        // тестирование с одной пересадкой
        List<Station> actualOneTransfer = routeCalculator.getShortestRoute(stationIndexTest.getStation("Первая_2_1"), stationIndexTest.getStation("Третья_3_3"));
        assertEquals(routeWithOneTransfer, actualOneTransfer);

        //тестирование с двумя пересадками
        List<Station> actualTwoTransfers = routeCalculator.getShortestRoute(stationIndexTest.getStation("Пятая_1_5"), stationIndexTest.getStation("Третья_3_3"));
        assertEquals(routeWithTwoTransfers, actualTwoTransfers);

    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
