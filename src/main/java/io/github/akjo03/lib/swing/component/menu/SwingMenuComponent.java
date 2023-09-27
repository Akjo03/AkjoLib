package io.github.akjo03.lib.swing.component.menu;

import io.github.akjo03.lib.swing.component.SwingComponent;

import javax.swing.*;
import java.awt.*;

@FunctionalInterface
@SuppressWarnings("unused")
public interface SwingMenuComponent<T extends Component & MenuElement> extends SwingComponent<T> {}