package tests;

import objects.Booking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import steps.Steps;

import java.util.*;

import static helpers.RandomUtils.getRandomItem;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class GetBookingIdsTests {
    final Logger logger = LoggerFactory.getLogger(GetBookingIdsTests.class);
    Steps steps = new Steps();

    @Test
    public void checkAllBookingIdsUniqueTest() {
        List<Integer> bookingIds = steps.getBookingIds();
        Set<Integer> uniqueBookingIds = new HashSet<>(bookingIds);
        assertEquals(uniqueBookingIds.size(), bookingIds.size());
    }

    @Test(description = "проверка поиска бронирования по имени - ID бронирования присутствует в общем списке бронирований")
    public void checkAllBookingIdsByFirstname() {
        List<Integer> bookingIds = steps.getBookingIds();
        String bookingIdToFilter = String.valueOf(getRandomItem(bookingIds));
        logger.info("для поиска выбрано бронирование с ID :: {}", bookingIdToFilter);
        Booking bookingToFilter = steps.getBooking(bookingIdToFilter);
        logger.info("Поле firstname искомого бронирования :: {}", bookingToFilter.getFirstname());
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("firstname", bookingToFilter.getFirstname());
        List<Integer> filteredBookingIds = steps.getBookingIdsByParams(paramsMap);
        assertTrue(filteredBookingIds.contains(Integer.parseInt(bookingIdToFilter)), "В общем списке bookings нет booking с id :: " + bookingIdToFilter);
    }
}
