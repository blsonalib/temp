package com.moodanalyse;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class MoodAnalyserReflector{

        public static Constructor<?>getConstructor(Class<?>...param) {
            try {
                Class<?> moodAnalyseClass = Class.forName("com.moodanalyse.MoodAnalyser");
                try {
                    moodAnalyseClass.getConstructor(param);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        public static Object getAnalyseMethod(Constructor<?> constructor,Object...message) {
            try {
                constructor.newInstance(message);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }


    public static MoodAnalyser createMoodAnalyse() throws MoodAnalysisException
    {
        try
        {
            Class<?> moodAnalyseClass = Class.forName("com.moodanalyse.MoodAnalyser");
            Constructor<?> moodConstructor = null;
            moodConstructor = moodAnalyseClass.getConstructor();
            Object moodObj = null;
            moodObj = moodConstructor.newInstance();
            return (MoodAnalyser) moodObj;
        }
        catch (ClassNotFoundException | NoSuchMethodException e)
        {
            throw new MoodAnalysisException(MoodAnalysisException.ExceptionType.NO_SUCH_CLASS, "Please enter the proper class name");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static MoodAnalyser createMoodAnalyseWithParameter(String message) throws MoodAnalysisException
    {
        try
        {
            Class<?> moodAnalyseClass = Class.forName("com.moodanalyse.MoodAnalyser");
            Constructor<?> moodConstructor = null;
            try
            {
                moodConstructor = moodAnalyseClass.getConstructor(String.class);
            }
            catch (NoSuchMethodException e)
            {
                throw new MoodAnalysisException(MoodAnalysisException.ExceptionType.NO_SUCH_METHOD, "please enter the proper method name");
            }
            Object moodObj = null;
            try
            {
                moodObj = moodConstructor.newInstance(message);
            }
            catch (InstantiationException | InvocationTargetException e)
            {
                throw new MoodAnalysisException(MoodAnalysisException.ExceptionType.OBJECT_CREATION_ISSUE, "Mey be invocation issue",e);
            }
            catch (IllegalAccessException e)
            {
                throw new MoodAnalysisException(MoodAnalysisException.ExceptionType.No_ACCESS, "Mey be invocation issue",e);
            }

            return (MoodAnalyser) moodObj;
        }
        catch (ClassNotFoundException e)
        {
            throw new MoodAnalysisException(MoodAnalysisException.ExceptionType.NO_SUCH_CLASS, "Please enter the proper class name");
        }
    }

    public static Object invokeMethod(Object moodAnalyserObject,String methodName) throws MoodAnalysisException {
        try {
                return moodAnalyserObject.getClass().getMethod(methodName).invoke(moodAnalyserObject);
            } catch (NoSuchMethodException e) {
            throw new MoodAnalysisException(MoodAnalysisException.ExceptionType.NO_SUCH_METHOD, "Define proper name method");
        }
        catch (IllegalAccessException  e) {
            throw new MoodAnalysisException(MoodAnalysisException.ExceptionType.No_ACCESS, "Mey be invocation issue",e);
        }
        catch (InvocationTargetException e) {
            throw new MoodAnalysisException(MoodAnalysisException.ExceptionType.METHOD_INVOCATION_ISSUE, "Mey be invocation issue",e);
        }

    }

    public static void setFieldValue(Object myObject, String fieldName, String fieldValue ) throws MoodAnalysisException {
        try {
            Field field=myObject.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            try {
                field.set(myObject,fieldValue);
            } catch (IllegalAccessException e) {
                throw new MoodAnalysisException(MoodAnalysisException.ExceptionType.No_ACCESS, "Mey be invocation issue",e);
            }
        } catch (NoSuchFieldException e) {

                throw new MoodAnalysisException(MoodAnalysisException.ExceptionType.No_SUCH_FIELD, "Define proper field name",e);
        }
    }



}


