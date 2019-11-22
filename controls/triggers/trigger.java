package controls.triggers;

import java.awt.event.ActionListener;

import jdk.javadoc.internal.doclets.formats.html.DeprecatedListWriter;

// 액션리스너 변수들 모듬
public final class trigger{
	public static final ActionListener csvOpen = new csvOpen();
	public static final ActionListener csvSave = new csvSave();
	public static final ActionListener drawAction = new DrawAction();

	
}