package lwjgl.study.demo1;

import java.awt.event.KeyEvent;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

/***
 * 学习demo
 * 
 * @author ligson
 * @see https://github.com/LWJGL/lwjgl3-demos
 * @see http://www.glfw.org/docs/latest/window.html
 *
 */
public class Demo1 {
	public static void main(String[] args) {
		// 初始化判断
		if (glfwInit() != 1) {
			System.out.println("flfwInit error");
			System.exit(-1);
		}

		// 创建窗口
		long window = glfwCreateWindow(500, 300, "aaa", NULL, NULL);
		if (window == NULL) {
			glfwTerminate();
		}

		// 键盘事件
		glfwSetKeyCallback(window, new GLFWKeyCallback() {

			@Override
			public void invoke(long window, int key, int scancode, int action,
					int mods) {
				// TODO Auto-generated method stub
				System.out.println("点击了:" + key);
				if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
					glfwSetWindowShouldClose(window, GL_TRUE);
				}

			}

		});

		// 窗口关闭事件

		glfwSetWindowCloseCallback(window, new GLFWWindowCloseCallback() {

			@Override
			public void invoke(long window) {
				// TODO Auto-generated method stub
				System.out.println("窗口关闭回调");
			}

		});

		// 声明要使用opengl
		glfwMakeContextCurrent(window);

		while (glfwWindowShouldClose(window) == GLFW_FALSE) {
			//轮询事件
			glfwPollEvents();
		}

	}
}
