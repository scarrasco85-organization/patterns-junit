package com.patterns.mockito.observer1alan;

import com.patterns.behavioral.observer.observer1.Weather;
import com.patterns.behavioral.observer.observer1.WeatherObserver;
import com.patterns.behavioral.observer.observer1.WeatherType;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WeatherTest {


    // captors:
    @Captor
    ArgumentCaptor<WeatherType> weatherTypeCaptor;

    // dependencias
    @Mock
    WeatherObserver observer;

    // SUT - dependiente
    @InjectMocks
    Weather weather;

    @Test
    @DisplayName("Change weather addObserver")
    public void changeWeather() {

        weather.addObserver(observer);
        weather.changeWeather(WeatherType.RAINY);

        ArgumentCaptor<WeatherType> argumentCaptor = ArgumentCaptor.forClass(WeatherType.class);
        verify(observer).update(argumentCaptor.capture());
        assertEquals(WeatherType.RAINY, argumentCaptor.getValue());

    }

    @Test
    @DisplayName("Change weather addObserver")
    public void changeWeather2() {

        weather.addObserver(observer);
        weather.changeWeather(WeatherType.RAINY);
        weather.changeWeather(WeatherType.RAINY);

        ArgumentCaptor<WeatherType> argumentCaptor = ArgumentCaptor.forClass(WeatherType.class);
        verify(observer, times(2)).update(argumentCaptor.capture());
        assertEquals(WeatherType.RAINY, argumentCaptor.getValue());

    }

    @Test
    @DisplayName("Change weather addObserver")
    public void changeWeather3() {

        weather.addObserver(observer);
        weather.changeWeather(WeatherType.RAINY);
        weather.changeWeather(WeatherType.SUNNY);

        ArgumentCaptor<WeatherType> argumentCaptor = ArgumentCaptor.forClass(WeatherType.class);
        verify(observer, times(2)).update(argumentCaptor.capture());

        List<WeatherType> arguments = argumentCaptor.getAllValues();
        assertEquals(WeatherType.RAINY, arguments.get(0));
        assertEquals(WeatherType.SUNNY, arguments.get(1));

    }

    @Test
    @DisplayName("Change weather 4")
    public void changeWeather4() {
        weather.addObserver(observer);
        weather.changeWeather(WeatherType.RAINY);

        verify(observer).update(weatherTypeCaptor.capture());
        assertEquals(WeatherType.RAINY, weatherTypeCaptor.getValue());
    }


    @Test
    @DisplayName("Change Weather 5")
    public void changeWeather5() {
        weather.addObserver(observer);
        weather.changeWeather(WeatherType.RAINY);
        verify(observer).update(argThat(weatherType -> weatherType == WeatherType.RAINY));
    }


    @Test
    @DisplayName("Change weather removeObserver")
    public void changeWeatherRemoveObserver() {
        weather.removeObserver(observer);
        weather.changeWeather(WeatherType.RAINY);

        verify(observer, never()).update(any(WeatherType.class));

    }
}
