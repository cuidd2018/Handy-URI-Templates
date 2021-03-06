/*
 * 
 */
package com.damnhandy.uri.template.impl;

import com.damnhandy.uri.template.DefaultVarExploder;
import com.damnhandy.uri.template.VarExploder;

/**
 * 
 * 
 * @author <a href="ryan@damnhandy.com">Ryan J. McDonough</a>
 * @version $Revision: 1.1 $
 */
public final class VarExploderFactory
{
   //private static final String DEFAULT_MSG = "";

   private VarExploderFactory()
   {
   }

   /**
    * 
    * 
    * @param varValue
    * @return
    */
   public static VarExploder getExploder(Object varValue, VarSpec varSpec)
   {
      if (varValue instanceof VarExploder)
      {
         return (VarExploder) varValue;
      }

//      if (varValue.getClass().isAnnotationPresent(ExplodeWith.class))
//      {
//         Class<?> exploderClass = varValue.getClass().getAnnotation(ExplodeWith.class).value();
//         return fromCustom(exploderClass, varValue, varSpec);
//      }
      return new DefaultVarExploder(varValue);
   }


//   private static VarExploder fromCustom(Class<?> exploderClass, Object varValue, VarSpec varSpec)
//   {
//      if (exploderClass.isAssignableFrom(VarExploder.class))
//      {
//         String msg = buildErrorMessage(
//               exploderClass.getSimpleName() + " must be an instance of " + VarExploder.class.getSimpleName(),
//               exploderClass, varValue, varSpec);
//         throw new VariableExpansionException(msg);
//      }
//
//      try
//      {
//         Constructor<?> constructor = exploderClass.getConstructor(Object.class);
//         VarExploder exploder = (VarExploder) constructor.newInstance(varValue);
//         return exploder;
//      }
//
//      catch (IllegalArgumentException e)
//      {
//         throwException(DEFAULT_MSG, e, exploderClass, varValue, varSpec);
//      }
//      catch (NoSuchMethodException e)
//      {
//         throwException(DEFAULT_MSG, e, exploderClass, varValue, varSpec);
//      }
//      catch (InstantiationException e)
//      {
//         throwException(DEFAULT_MSG, e, exploderClass, varValue, varSpec);
//      }
//      catch (IllegalAccessException e)
//      {
//         throwException(DEFAULT_MSG, e, exploderClass, varValue, varSpec);
//      }
//      catch (InvocationTargetException e)
//      {
//         throwException(DEFAULT_MSG, e, exploderClass, varValue, varSpec);
//      }
//      return null;
//   }


//   private static void throwException(String message, Throwable e, Class<?> exploderClass, Object varValue,
//         VarSpec varSpec) throws VariableExpansionException
//   {
//      String msg = buildErrorMessage(message, exploderClass, varValue, varSpec);
//      throw new VariableExpansionException(msg, e);
//   }

//   private static String buildErrorMessage(String message, Class<?> exploderClass, Object varValue, VarSpec varSpec)
//   {
//      StringBuilder b = new StringBuilder();
//      b.append(message)
//       .append("[ varspec: ").append(varSpec)
//       .append(" variable value:").append(varValue)
//       .append(" exploder class: ").append(exploderClass.getName()).append("]");
//      return b.toString();
//   }
}
