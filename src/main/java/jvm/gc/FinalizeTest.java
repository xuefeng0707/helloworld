package jvm.gc;

/**
 * 一个对象被GC之前，会被“标记”两次。第一次标记是对根搜索后不可达(通过GC Roots引用不到)的对象进行标记，然后判断是否需要执行finalize()，
 * 如果需要执行，则把此对象放到一个名为F-Queue的队列中，由Finalizer线程来执行队列中所以对象的finalize()，此线程优先级较低，且
 * 不保证会执行。第二次标记时，会检查F-Queue中的对象，如果队列中某对象已经变为可达，则把它从“即将回收”的集合中删除。
 * 判断是否需要执行finalize()：
 *  1) 类是否覆盖了finalize()方法
 *  2) finalize()方式是否已经被执行过，如果已经执行过，则不会再执行
 * User: xuefeng
 * Date: 14-3-28
 * Time: 下午11:40
 */
public class FinalizeTest {
	private static FinalizeTest SAVE_HOOK = null;

	// finalize()只会被执行一次
	@Override
	protected void finalize() throws Throwable {
		super.finalize();

		FinalizeTest.SAVE_HOOK = this;
		System.out.println("finalize() executed");
	}

	public static void main(String[] args) throws Exception {
		SAVE_HOOK = new FinalizeTest();


		test(); // finalize() executed; SAVE_HOOK is alive now
		test(); // SAVE_HOOK is dead now
	}

	private static void test() throws Exception {
		SAVE_HOOK = null;

		System.gc();

		// Finalizer线程优先级较低
		Thread.sleep(1000);

		if(SAVE_HOOK != null) {
			System.out.println("SAVE_HOOK is alive now");
		} else {
			System.out.println("SAVE_HOOK is dead now");
		}
	}
}
