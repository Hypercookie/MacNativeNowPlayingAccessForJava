#import <Cocoa/Cocoa.h>
#import <jni.h>

#ifndef _ConversionUtils
#define _ConversionUtils


 /*
 * ConvertToNSString
 *
 * given a non-null jstring argument, return the equivalent NSString representation. The object is autoreleased.
 *
 * This function returns NULL if the argument is NULL, or if the NSString couldn't be created. Requires the JNIEnv
 * to be passed as the first argument
 *
 */
 NSString *ConvertToNSString(JNIEnv *env, jstring str);

 /*
 * ConvertToCFStringRef
 *
 * given a non-null jstring argument, return the equivalent CFStringRef representation. The object is retained.
 *
 * This function returns NULL if the argument is NULL, or if the CFStringRef couldn't be created. Requires the JNIEnv
 * to be passed as the first argument
 *
 */
 CFStringRef ConvertToCFStringRef(JNIEnv *env, jstring str);


/*
 * CreateJavaStringFromNSString
 *
 * given a non-null NSString argument, return the equivalent Java String representation.
 *
 * This function returns NULL if the argument is NULL, or if the jstring couldn't be created. Requires the JNIEnv
 * to be passed as the first argument
 *
 */
jstring CreateJavaStringFromNSString(JNIEnv *env, NSString *nativeStr);


/*
 * CreateJavaStringFromCFStringRef
 *
 * given a non-null CFStringRef argument, return the equivalent Java String representation.
 *
 * This function returns NULL if the argument is NULL, or if the jstring couldn't be created. Requires the JNIEnv
 * to be passed as the first argument
 *
 */
jstring CreateJavaStringFromCFStringRef(JNIEnv *env, CFStringRef nativeStr);

#endif