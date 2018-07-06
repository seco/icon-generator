package com.sshtools.icongenerator;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sshtools.icongenerator.IconBuilder.IconShape;

/**
 * Very simple icon test. Just shows a grid of random icons. Remove the zero
 * seed from {@link Icons#r} if you want truly random every time.
 */
@SuppressWarnings("serial")
public class Icons extends JPanel {

	private Random r = new Random(0);

	Icons() {
		setLayout(new GridLayout(10, 10, 2, 2));
		for (int i = 0; i < 100; i++) {
			IconBuilder ib = new IconBuilder();
			if (r.nextFloat() > 0.5)
				ib.bold(true);
			if (r.nextFloat() > 0.5)
				ib.border((int) (r.nextFloat() * 4f));
			ib.shape(IconShape.values()[(int) (IconShape.values().length * r.nextFloat())]);
			ib.width(48);
			ib.height(48);
			if (r.nextFloat() > 0.5) {
				ib.icon(AwesomeIcon.values()[(int) (AwesomeIcon.values().length * r.nextFloat())]);
				if (r.nextFloat() > 0.5)
					ib.text(randWord());
			} else
				ib.text(randWord());
			ib.fontName("Sans");
			ib.color(Colors.MATERIAL.color(ib.text()));
			add(new JLabel(ib.build(Icon.class)));
		}
	}

	String randWord() {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < 1 + (int) (r.nextFloat() * 3); i++) {
			if (r.nextFloat() > 0.5)
				b.append((char) ('a' + (int) (r.nextFloat() * 26f)));
			else
				b.append((char) ('A' + (int) (r.nextFloat() * 26f)));
		}
		return b.toString();
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.getContentPane().setLayout(new BorderLayout());
		Icons icons = new Icons();
		f.getContentPane().add(icons);
		f.pack();
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}
}
