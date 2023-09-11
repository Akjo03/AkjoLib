package io.github.akjo03.lib.swing.util.window;

import com.formdev.flatlaf.FlatLaf;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.Objects;
import java.util.function.Consumer;

@SuppressWarnings("unused")
public final class SwingWindow {
	private static Rectangle getScreenSpace() {
		GraphicsDevice[] devices = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
		Rectangle screenSpace = new Rectangle();
		for (GraphicsDevice device : devices) {
			screenSpace = screenSpace.union(device.getDefaultConfiguration().getBounds());
		}
		return screenSpace;
	}

	public static void initialize(@NotNull JFrame frame, @NotNull String appName) throws Exception {
		UIManager.put("JRootPane.menuBarEmbedded", true);
		UIManager.put("JComponent.outline", Color.getColor("#3498db"));
		UIManager.put("h00.font", "+24");
		UIManager.put("h0.font", "+18");
		UIManager.put("h1.font", "+12 $semibold.font");
		UIManager.put("h2.font", "+10 $semibold.font");
		UIManager.put("h3.font", "+6 $semibold.font");
		UIManager.put("h4.font", "+2 $semibold.font");
		UIManager.put("large.font", "+4");
		UIManager.put("medium.font", "+2");
		UIManager.put("defaultFont", new Font("Inter", Font.PLAIN, 12));
		UIManager.put("small.font", "-2");
		UIManager.put("mini.font", "-4");
		FlatLaf.updateUI();

		frame.setTitle(appName);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize(new Dimension(1920, 1080));
		frame.setMinimumSize(new Dimension(800, 600));

		if (!SwingWindowProperties.exists(appName)) {
			frame.setLocationRelativeTo(null);
			SwingWindowProperties properties = SwingWindowProperties.initialize(
					appName.replace(' ', '_').toLowerCase(),
					frame.getWidth(), frame.getHeight(),
					frame.getX(), frame.getY(),
					frame.getExtendedState()
			);
		} else {
			SwingWindowProperties properties = SwingWindowProperties.load(
					frame,
					appName.replace(' ', '_').toLowerCase()
			);
			frame.setSize(
					getSafeWidth(properties.getWindowWidth(), properties.getWindowPosX()),
					getSafeHeight(properties.getWindowHeight(), properties.getWindowPosY())
			);
			frame.setLocation(
					getSafeX(properties.getWindowPosX(), properties.getWindowWidth()),
					getSafeY(properties.getWindowPosY(), properties.getWindowHeight())
			);
			frame.setExtendedState(properties.getWindowState());
		}

		SwingWindow.onWindowResized(frame, (event) -> {
			try {
				if (frame.getExtendedState() == Frame.MAXIMIZED_BOTH) return;
				SwingWindowProperties.saveSize(appName, frame.getWidth(), frame.getHeight());
			} catch (Exception ignored) {}
		});
		SwingWindow.onWindowMoved(frame, (event) -> {
			try {
				if (frame.getExtendedState() == Frame.MAXIMIZED_BOTH) return;
				SwingWindowProperties.savePosition(appName, frame.getX(), frame.getY());
			} catch (Exception ignored) {}
		});
		SwingWindow.onWindowMaximized(frame, (event) -> {
			try {
				SwingWindowProperties.saveState(appName, Frame.MAXIMIZED_BOTH);
			} catch (Exception ignored) {}
		});
		SwingWindow.onWindowRestored(frame, (event) -> {
			try {
				SwingWindowProperties.saveState(appName, Frame.NORMAL);
			} catch (Exception ignored) {}
		});

		URL iconResource = ClassLoader.getSystemClassLoader().getResource("icon.png");
		if (Objects.nonNull(iconResource)) {
			frame.setIconImage(Toolkit.getDefaultToolkit().getImage(iconResource));
		}

		frame.setVisible(true);
	}

	private static int getSafeWidth(int width, int posX) {
		Rectangle screenSpace = getScreenSpace();
		if (width > screenSpace.width) return screenSpace.width;
		else if (posX + width > screenSpace.width) return screenSpace.width - posX;
		else return width;
	}

	private static int getSafeHeight(int height, int posY) {
		Rectangle screenSpace = getScreenSpace();
		if (height > screenSpace.height) return screenSpace.height;
		else if (posY + height > screenSpace.height) return screenSpace.height - posY;
		else return height;
	}

	private static int getSafeX(int posX, int width) {
		Rectangle screenSpace = getScreenSpace();
		if (posX < 0) return 0;
		else if (posX + width > screenSpace.width) return screenSpace.width - width;
		else return posX;
	}

	private static int getSafeY(int posY, int height) {
		Rectangle screenSpace = getScreenSpace();
		if (posY < 0) return 0;
		else if (posY + height > screenSpace.height) return screenSpace.height - height;
		else return posY;
	}

	public static void onWindowOpened(@NotNull Frame frame, @NotNull Consumer<WindowEvent> callback) {
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				callback.accept(e);
			}
		});
	}

	public static void onWindowClosing(@NotNull Frame frame, @NotNull Consumer<WindowEvent> callback) {
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				callback.accept(e);
			}
		});
	}

	public static void onWindowFocusLost(@NotNull Frame frame, @NotNull Consumer<WindowEvent> callback) {
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowLostFocus(WindowEvent e) {
				callback.accept(e);
			}
		});
	}

	public static void onWindowMinimized(@NotNull Frame frame, @NotNull Consumer<WindowEvent> callback) {
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowIconified(WindowEvent e) {
				callback.accept(e);
			}
		});
	}

	public static void onWindowMaximized(@NotNull Frame frame, @NotNull Consumer<ComponentEvent> callback) {
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				if (frame.getExtendedState() == Frame.MAXIMIZED_BOTH) callback.accept(e);
			}
		});
	}

	public static void onWindowRestored(@NotNull Frame frame, @NotNull Consumer<ComponentEvent> callback) {
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				if (frame.getExtendedState() == Frame.NORMAL) callback.accept(e);
			}
		});
	}

	public static void onWindowResized(@NotNull Frame frame, @NotNull Consumer<ComponentEvent> callback) {
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				callback.accept(e);
			}
		});
	}

	public static void onWindowMoved(@NotNull Frame frame, @NotNull Consumer<ComponentEvent> callback) {
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentMoved(ComponentEvent e) {
				callback.accept(e);
			}
		});
	}
}