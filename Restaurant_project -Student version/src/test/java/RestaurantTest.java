import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeEach
    public void setup() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        //LocalTime present_time = closingTime.minusHours(5);
        System.out.println(restaurant.getMenu());
        LocalTime present_time = LocalTime.parse("15:45:32");
        Restaurant mockito_restaurant = Mockito.spy(restaurant);
        Mockito.when(mockito_restaurant.getCurrentTime()).thenReturn(present_time);
        assertTrue(mockito_restaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        //LocalTime present_time = openingTime.minusMinutes(30);
        LocalTime present_time = LocalTime.parse("10:29:59");
        Restaurant mockito_restaurant = Mockito.spy(restaurant);
        Mockito.when(mockito_restaurant.getCurrentTime()).thenReturn(present_time);
        assertFalse(mockito_restaurant.isRestaurantOpen());
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class,()->restaurant.removeFromMenu("French fries"));
    }

    @Test
    public void selecting_items_in_menu_should_return_total_cost() {
        Restaurant mockito_restaurant = Mockito.spy(restaurant);
        mockito_restaurant.addToMenu("Masala Pappad", 100);
        mockito_restaurant.addToMenu("Jeera rice", 350);
        mockito_restaurant.addToMenu("Butter Milk", 125);
        //int testTotal = 0;
        List<String> selected_items = new ArrayList<>();
        selected_items.add("Masala Pappad");
        selected_items.add("Jeera rice");
        assertEquals(450, mockito_restaurant.orderedTotalCost(selected_items));
    }



    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



}