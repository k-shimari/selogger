package selogger.weaver;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.objectweb.asm.ClassReader;

import selogger.EventType;
import selogger.logging.EventLogger;
import selogger.logging.io.MemoryLogger;
import selogger.weaver.method.Descriptor;


public class WeaverTest {

	private WeaveLog weaveLog;
	private Class<?> wovenClass;
	private MemoryLogger memoryLogger;
	
	@Before
	public void setup() throws IOException {
		String className = "selogger/testdata/SimpleTarget";
		ClassReader r = new ClassReader(className);
		weaveLog = new WeaveLog(0, 0, 0);
		WeaverConfig config = new WeaverConfig(WeaverConfig.KEY_RECORD_DEFAULT); 
		ClassTransformer c = new ClassTransformer(weaveLog, config, r, this.getClass().getClassLoader());
		wovenClass = new WeaveClassLoader().createClass("selogger.testdata.SimpleTarget", c.getWeaveResult());
		memoryLogger = EventLogger.initializeForTest();
	}

	public class EventIterator {
		
		private int eventIndex;
		
		public EventIterator() {
			eventIndex = -1;
		}
		
		/**
		 * Proceed to the next event.
		 * This method must be called before calling other getter methods.
		 * @return true if the event data is available.
		 * False indicate the end of data.
		 */
		public boolean next() {
			eventIndex++;
			return eventIndex < memoryLogger.getEvents().size();
		}
		
		public String getClassName() {
			int dataId = memoryLogger.getEvents().get(eventIndex).getDataId();
			int methodId = weaveLog.getDataEntries().get(dataId).getMethodId();
			return weaveLog.getMethods().get(methodId).getClassName();
		}
		
		public String getMethodName() {
			int dataId = memoryLogger.getEvents().get(eventIndex).getDataId();
			int methodId = weaveLog.getDataEntries().get(dataId).getMethodId();
			return weaveLog.getMethods().get(methodId).getMethodName();
		}
		
		public EventType getEventType() {
			int dataId = memoryLogger.getEvents().get(eventIndex).getDataId();
			return weaveLog.getDataEntries().get(dataId).getEventType();
		}

		public int getIntValue() {
			return memoryLogger.getEvents().get(eventIndex).getIntValue();
		}
		
		public short getShortValue() {
			return memoryLogger.getEvents().get(eventIndex).getShortValue();
		}

		public Object getObjectValue() {
			return memoryLogger.getEvents().get(eventIndex).getObjectValue();
		}

		public Class<?> getValueType() {
			return memoryLogger.getEvents().get(eventIndex).getValueType();
		}

		public Descriptor getDataIdValueDesc() {
			int dataId = memoryLogger.getEvents().get(eventIndex).getDataId();
			return weaveLog.getDataEntries().get(dataId).getValueDesc();
		}
		
		public String getAttributes() {
			int dataId = memoryLogger.getEvents().get(eventIndex).getDataId();
			return weaveLog.getDataEntries().get(dataId).getAttributes();
		}
	}
	
	private void testBaseEvents(EventIterator it, Object instance) {
		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.METHOD_ENTRY, it.getEventType());
		Assert.assertEquals("<clinit>", it.getMethodName());
		
		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.PUT_STATIC_FIELD, it.getEventType());
		Assert.assertEquals(1, it.getIntValue());

		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.METHOD_NORMAL_EXIT, it.getEventType());
		Assert.assertEquals(Descriptor.Void, it.getDataIdValueDesc());
		
		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.METHOD_ENTRY, it.getEventType());
		Assert.assertEquals("<init>", it.getMethodName());
		Assert.assertEquals("selogger/testdata/SimpleTarget", it.getClassName());
		
		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.CALL, it.getEventType());
		Assert.assertEquals("<init>", it.getMethodName());
		Assert.assertTrue(it.getAttributes().contains("java/lang/Object"));
		Assert.assertTrue(it.getAttributes().contains("CallType=ReceiverNotInitialized"));

		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.NEW_OBJECT_INITIALIZED, it.getEventType());
		Assert.assertSame(instance, it.getObjectValue());
		
		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.PUT_INSTANCE_FIELD, it.getEventType());
		Assert.assertSame(instance, it.getObjectValue());
		
		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.PUT_INSTANCE_FIELD_VALUE, it.getEventType());
		Assert.assertEquals(Descriptor.Integer, it.getDataIdValueDesc());
		Assert.assertEquals(int.class, it.getValueType());
		Assert.assertEquals(2, it.getIntValue());

		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.METHOD_NORMAL_EXIT, it.getEventType());
		Assert.assertEquals(Descriptor.Void, it.getDataIdValueDesc());

	}
	
	@Test
	public void testWeaving() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
		// Event generation
		Object o = wovenClass.newInstance();
		
		EventIterator it = new EventIterator();

		// Check events
		testBaseEvents(it, o);
		
		// Execute another method
		Method method = wovenClass.getMethod("getField", new Class<?>[0]);
		method.invoke(o, (Object[])null);

		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.METHOD_ENTRY, it.getEventType());
		Assert.assertEquals("getField", it.getMethodName());
		Assert.assertEquals("selogger/testdata/SimpleTarget", it.getClassName());
		
		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.FORMAL_PARAM, it.getEventType());
		Assert.assertEquals(Descriptor.Object, it.getDataIdValueDesc());
		Assert.assertEquals(o, it.getObjectValue());

		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.GET_INSTANCE_FIELD, it.getEventType());
		Assert.assertEquals(Descriptor.Object, it.getDataIdValueDesc());
		Assert.assertEquals(o, it.getObjectValue());

		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.GET_INSTANCE_FIELD_RESULT, it.getEventType());
		Assert.assertEquals(Descriptor.Integer, it.getDataIdValueDesc());
		Assert.assertEquals(2, it.getIntValue());

		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.METHOD_NORMAL_EXIT, it.getEventType());
		Assert.assertEquals(Descriptor.Integer, it.getDataIdValueDesc());
		Assert.assertEquals(2, it.getIntValue());


	}

	@Test
	public void testArray() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
		// Event generation
		Object o = wovenClass.newInstance();
		
		EventIterator it = new EventIterator();

		// Check events
		testBaseEvents(it, o);

		// Execute a method
		Method createArray = wovenClass.getMethod("createArray", new Class<?>[] {int.class});
		Object array = createArray.invoke(o, 2);

		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.METHOD_ENTRY, it.getEventType());
		Assert.assertEquals("createArray", it.getMethodName());
		Assert.assertEquals("selogger/testdata/SimpleTarget", it.getClassName());
		
		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.FORMAL_PARAM, it.getEventType());
		Assert.assertEquals(Descriptor.Object, it.getDataIdValueDesc());
		Assert.assertEquals(o, it.getObjectValue());

		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.FORMAL_PARAM, it.getEventType());
		Assert.assertEquals(Descriptor.Integer, it.getDataIdValueDesc());
		Assert.assertEquals(2, it.getIntValue());

		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.NEW_ARRAY, it.getEventType());
		Assert.assertEquals(2, it.getIntValue());
		Assert.assertTrue(it.getAttributes().contains("ElementType=short"));

		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.NEW_ARRAY_RESULT, it.getEventType());
		Assert.assertSame(array, it.getObjectValue());

		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.ARRAY_LENGTH, it.getEventType());
		Assert.assertSame(array, it.getObjectValue());
		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.ARRAY_LENGTH_RESULT, it.getEventType());
		Assert.assertEquals(2, it.getIntValue());

		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.ARRAY_STORE, it.getEventType());
		Assert.assertSame(array, it.getObjectValue());
		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.ARRAY_STORE_INDEX, it.getEventType());
		Assert.assertEquals(0, it.getIntValue());
		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.ARRAY_STORE_VALUE, it.getEventType());
		Assert.assertEquals(0, it.getShortValue());

		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.ARRAY_LENGTH, it.getEventType());
		Assert.assertSame(array, it.getObjectValue());
		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.ARRAY_LENGTH_RESULT, it.getEventType());
		Assert.assertEquals(2, it.getIntValue());

		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.ARRAY_STORE, it.getEventType());
		Assert.assertSame(array, it.getObjectValue());
		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.ARRAY_STORE_INDEX, it.getEventType());
		Assert.assertEquals(1, it.getIntValue());
		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.ARRAY_STORE_VALUE, it.getEventType());
		Assert.assertEquals(1, it.getShortValue());

		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.ARRAY_LENGTH, it.getEventType());
		Assert.assertSame(array, it.getObjectValue());
		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.ARRAY_LENGTH_RESULT, it.getEventType());
		Assert.assertEquals(2, it.getIntValue());

		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.ARRAY_LOAD, it.getEventType());
		Assert.assertSame(array, it.getObjectValue());
		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.ARRAY_LOAD_INDEX, it.getEventType());
		Assert.assertEquals(0, it.getIntValue());
		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.ARRAY_LOAD_RESULT, it.getEventType());
		Assert.assertEquals(0, it.getShortValue());

		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.ARRAY_STORE, it.getEventType());
		Assert.assertSame(array, it.getObjectValue());
		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.ARRAY_STORE_INDEX, it.getEventType());
		Assert.assertEquals(0, it.getIntValue());
		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.ARRAY_STORE_VALUE, it.getEventType());
		Assert.assertEquals(1, it.getShortValue());

		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.ARRAY_STORE, it.getEventType());
		Assert.assertSame(array, it.getObjectValue());
		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.ARRAY_STORE_INDEX, it.getEventType());
		Assert.assertEquals(1, it.getIntValue());
		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.ARRAY_STORE_VALUE, it.getEventType());
		Assert.assertEquals(2, it.getShortValue());
		
		Assert.assertTrue(it.next());
		Assert.assertEquals(EventType.METHOD_NORMAL_EXIT, it.getEventType());
		Assert.assertEquals(Descriptor.Object, it.getDataIdValueDesc());
		Assert.assertSame(array, it.getObjectValue());
}
	/*
	 * test cases:
	 * �^�Ƃ��� float, double, boolean, byte ���g��
	 * �������胁�\�b�h. FORMAL_PARAM
	 * ��O�𔭐������Ă̏I��
	 * ��O�𔭐������Ă� CATCH �ւ̈ړ�
	 * ��O�� THROW����
	 * ���\�b�h�Ăяo���ŗ�O�𔭐�������
	 * ���\�b�h�Ăяo�����琳��ɖ߂�l���󂯎��
	METHOD_EXCEPTIONAL_EXIT_LABEL,  
	METHOD_EXCEPTIONAL_EXIT, 
	CATCH, 
	THROW, 
	CALL, 
	ACTUAL_PARAM, 
	CALL_RETURN, 
	 * Static �t�B�[���h��ǂݏo��
	 * �����N���X�ɑ΂���l�̐ݒ�
	GET_STATIC_FIELD, 
	PUT_INSTANCE_FIELD_BEFORE_INITIALIZATION,

	�z�񂩂�̒l�̓ǂݏo��
	�z�񂩂�̒l�̓ǂݏo���ŗ�O����
	�z��ւ̒l�̓ǂݏo�����
	ARRAY_LOAD_FAIL,
	�������z��̐���
	MULTI_NEW_ARRAY, 
	MULTI_NEW_ARRAY_CONTENT,
	Synchronized�u���b�N�̎��s
	MONITOR_ENTER, 
	MONITOR_EXIT,  
	�萔������̃��[�h
	CONSTANT_OBJECT_LOAD, 
	�V�K�I�u�W�F�N�g�쐬�A�i�ł���� NEW ���i�\���j
	NEW_OBJECT, 
	NEW_OBJECT_CREATION_COMPLETED,
	�C���X�^���X����
	INSTANCEOF, 
	INSTANCEOF_RESULT,
	���x���ʉ�
	LABEL,
	JUMP,
	���[�J���ϐ�����
	LOCAL_LOAD, 
	LOCAL_STORE, 
	RET;

	 */
	
	public static class WeaveClassLoader extends ClassLoader {
		
		public Class<?> createClass(String name, byte[] bytecode) {
			Class<?> c = defineClass(name, bytecode, 0, bytecode.length);
			resolveClass(c);
			return c;
		}
	}
	
}
