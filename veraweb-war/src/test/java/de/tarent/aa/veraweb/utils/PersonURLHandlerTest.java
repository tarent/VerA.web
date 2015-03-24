package de.tarent.aa.veraweb.utils;

import de.tarent.aa.veraweb.beans.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Properties;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonURLHandlerTest {

    private PersonURLHandler personUrlHandler;

    @Mock
    private Person person;

    @Mock
    private PropertiesReader propertiesReader;

    @Before
    public void setUp() {
        personUrlHandler = new PersonURLHandler();
        personUrlHandler.setPropertiesReader(propertiesReader);
    }

    @Test
    public void testGetEventUrl() {
        // GIVEN

        Properties properties = mock(Properties.class);
        when(propertiesReader.propertiesAreAvailable()).thenReturn(true);
        when(propertiesReader.getProperties()).thenReturn(properties);
        when(properties.getProperty(URLGenerator.VERAWEB_ONLINEREG_PROTOCOL)).thenReturn("https");
        when(properties.getProperty(URLGenerator.VERAWEB_ONLINEREG_PORT)).thenReturn("8443");
        when(properties.getProperty(URLGenerator.VERAWEB_ONLINEREG_HOST)).thenReturn("localhost");

        // WHEN
        String url = personUrlHandler.generateResetPasswordUrl("3f03f6fb-5d22-44dd-b0a4-f63715db95ba");

        // THEN
        assertEquals("https://localhost:8443/#/reset/password/3f03f6fb-5d22-44dd-b0a4-f63715db95ba", url);
    }

    @Test
    public void testGetEventUrlFailed() {
        // WHEN
        String url = personUrlHandler.generateResetPasswordUrl(null);

        // THEN
        assertEquals("", url);
    }

}