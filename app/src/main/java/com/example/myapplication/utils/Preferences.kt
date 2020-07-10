package com.example.myapplication.utils

import android.content.Context

class Preferences {

    companion object {
        //ADD
        @JvmStatic
        fun addString(preference: String, name: String, context: Context){
            val prefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
            val namePref = context.packageName+"."+name
            prefs.edit().putString(namePref, preference).apply()
        }

        fun addBoolean(preference: Boolean, name: String, context: Context){
            val prefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
            val namePref = context.packageName+"."+name
            prefs.edit().putBoolean(namePref, preference).apply()
        }

        fun addInt(preference: Int, name: String, context: Context){
            val prefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
            val namePref = context.packageName+"."+name
            prefs.edit().putInt(namePref, preference).apply()
        }

        fun addLong(preference: Long, name: String, context: Context){
            val prefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
            val namePref = context.packageName+"."+name
            prefs.edit().putLong(namePref, preference).apply()
        }

        //GET
        @JvmStatic
        fun getPrefString(name: String, context: Context) : String{
            val prefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
            val namePref = context.packageName+"."+name
            val l = prefs.getString(namePref, "")
            return l!!
        }

        fun getBoolean(name: String, context: Context) : Boolean{
            val prefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
            val namePref = context.packageName+"."+name
            val l = prefs.getBoolean(namePref, false)
            return l
        }

        @JvmStatic
        fun getInt(name: String, context: Context) : Int{
            val prefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
            val namePref = context.packageName+"."+name
            val l = prefs.getInt(namePref, 0)
            return l
        }

        @JvmStatic
        fun getLong(name: String, context: Context) : Long{
            val prefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
            val namePref = context.packageName+"."+name
            val l = prefs.getLong(namePref, 0L)
            return l
        }

        //OTHER
        @JvmStatic
        fun preferenceExist(name: String, context: Context): Boolean{
            val prefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
            val namePref = context.packageName+"."+name
            val contains = prefs.contains(namePref)
            return contains
        }

        @JvmStatic
        fun preferenceDelete(name: String, context: Context){
            val prefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
            val namePref = context.packageName+"."+name
            val contains = prefs.edit().remove(namePref).commit()
        }

        fun getPrefNames(context: Context):MutableSet<String>{
            val prefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
            return prefs.all.keys
        }

        @JvmStatic
        fun deleteAllpreferences(context: Context){
            val prefs = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            editor.clear()
            editor.apply()
        }
    }
}