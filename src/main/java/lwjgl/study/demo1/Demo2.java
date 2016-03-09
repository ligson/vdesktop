package lwjgl.study.demo1;

import java.math.BigInteger;
import java.nio.ByteBuffer;

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
public class Demo2 {
	public static void main(String[] args) {
		// 初始化判断
		if (glfwInit() != 1) {
			System.out.println("flfwInit error");
			System.exit(-1);
		}

		int width = 500;
		int height = 300;

		// 创建窗口
		long window = glfwCreateWindow(width, height, "aaa", NULL, NULL);
		if (window == NULL) {
			glfwTerminate();
		}

		// 键盘事件
		glfwSetKeyCallback(window, new GLFWKeyCallback() {

			@Override
			public void invoke(long window, int key, int scancode, int action,
					int mods) {
				// TODO Auto-generated method stub
				String actionString = action == GLFW_RELEASE ? "松开" : "按下";
				System.out.println(actionString + "了:" + key);
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
		glfwSwapInterval(1);

		// 窗口监视器
		long monitor = glfwGetPrimaryMonitor();
		// 获取整个屏幕句柄
		GLFWVidMode videoMode = glfwGetVideoMode(monitor);

		// 窗口居中
		glfwSetWindowPos(window, (videoMode.width() - width) / 2,
				(videoMode.height() - width) / 2);

		// 不懂什么意思
		GL.createCapabilities();

		System.out.println(videoMode.width());
		// glViewport(0, 0, videoMode.width(), videoMode.height());

		// Set the clear color
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		while (glfwWindowShouldClose(window) == GLFW_FALSE) {

			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the

			glfwSwapBuffers(window); // swap the color buffers // framebuffer

			glBegin(GL_LINES);
			glColor3f(1.f, 0.f, 0.f);
			glVertex3f(0.25f, 0.25f, 0.0f);
			glVertex3f(0.75f, 0.25f, 0.0f);
			glEnd();
			glFlush();
			// 轮询事件
			glfwPollEvents();
		}

	}
}
