package org.infinitest.intellij;

import org.infinitest.intellij.plugin.swingui.InfinitestView;
import org.infinitest.util.LoggingListener;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Level.WARNING;

public class WhenLoggingMessage
{
    private LoggingListener listener;
    private InfinitestView view;

    @Before
    public void setUp()
    {
        view = mock(InfinitestView.class);
        listener = new InfinitestLoggingListener(view);
    }

    @Test
    public void shouldDisplayMesageInView()
    {
        listener.logMessage(INFO, "test message");
        verify(view).writeLogMessage(contains("test message"));
    }

    @Test
    public void shouldIncludeLogLevelInDisplayedMessage()
    {
        listener.logMessage(INFO, "test message");
        verify(view).writeLogMessage(contains("INFO"));
    }

    @Test
    public void shouldIncludeOtherLogLevelInDisplayedMessage()
    {
        listener.logMessage(WARNING, "test message");
        verify(view).writeLogMessage(contains("WARNING"));
    }

    @Test
    public void shouldLeftAlignLogLevelInTenCharacterField()
    {
        listener.logMessage(SEVERE, "test message");
        verify(view).writeLogMessage(contains("SEVERE    "));
    }
}