#import <Foundation/Foundation.h>
#import "MediaRemote.h"
#import "nativeLinkers_CurrentSongLinker.h"
#import "ConversionUtils.h"
#include <jni.h>


jstring CreateJavaStringFromNSString(JNIEnv *env, NSString *nativeStr)
{
    if (nativeStr == NULL)
    {
        return NULL;
    }
    // Note that length returns the number of UTF-16 characters,
    // which is not necessarily the number of printed/composed characters
    jsize buflength = [nativeStr length];
    unichar buffer[buflength];
    [nativeStr getCharacters:buffer];
    jstring javaStr = (*env)->NewString(env, (jchar *)buffer, buflength);
    return javaStr;
}
//
// jobject save_object;
// jmethodID save_method;
// JNIEnv *save_env;

// @interface GlobalState : NSObject
//
// - (void)infoDidChange:(NSNotification *)notification;
// @end
//
//
// @implementation GlobalState {
//
// }
// - (void)infoDidChange:(NSNotification *)notification {
//
// NSLog(@"WHDUA");
//        (*save_env)->CallVoidMethod(save_env,save_object,save_method,CreateJavaStringFromNSString(save_env,@"AUAUAU"));
// }
// @end

// JNIEXPORT void JNICALL Java_nativeLinkers_CurrentSongLinker_callback(JNIEnv *env, jobject jthis) {
//
//       //  (*envy) -> GetJavaVM(&jvm);
//      //   jweak store_listen = (*env)->NewWeakGlobalRef(env,jthis);
//         jclass clazz = (*env)->GetObjectClass(env,jthis);
//         jmethodID store_method = (*env)->GetMethodID(env,clazz,"registerSong","(Ljava/lang/String;)V");
//
//         (*env)->CallVoidMethod(env,jthis,store_method,CreateJavaStringFromNSString(env,@"AUAUAU"));
//
//         save_env = env;
//         save_method = store_method;
//         save_object = jthis;
//
//         GlobalState *sta = [[GlobalState alloc]init];
//         [[NSNotificationCenter defaultCenter] addObserver:sta selector:@selector(infoDidChange:) name:kMRMediaRemoteNowPlayingInfoDidChangeNotification object:nil];
//            MRMediaRemoteRegisterForNowPlayingNotifications(dispatch_get_main_queue());
//            MRMediaRemoteSetCanBeNowPlayingApplication(YES);
//
//          [[NSRunLoop currentRunLoop] run];
// }

JNIEXPORT jstring JNICALL Java_nativeLinkers_CurrentSongLinker_getCurrentSongFromNativeMacPlayBack
  (JNIEnv *env, jobject jthis) {
  @autoreleasepool {
          __block BOOL stop = NO;
          __block NSString *returnString = nil;
          MRMediaRemoteRegisterForNowPlayingNotifications(dispatch_get_main_queue());
          MRMediaRemoteSetCanBeNowPlayingApplication(YES);
          while (!stop) {
              MRMediaRemoteGetNowPlayingInfo(dispatch_get_main_queue(), ^(CFDictionaryRef result) {
                  NSString *out = @"{\"Title\" : \"";
                  NSDictionary *dict = (__bridge NSDictionary *) result;
                  NSString *title = dict[(__bridge NSString *) kMRMediaRemoteNowPlayingInfoTitle];
                  NSString *album = dict[(__bridge NSString *) kMRMediaRemoteNowPlayingInfoAlbum];
                  NSString *artist = dict[(__bridge NSString *) kMRMediaRemoteNowPlayingInfoArtist];
                  out = [out stringByAppendingString:title];
                  out = [out stringByAppendingString:@"\",\"Album\" : \""];
                  out = [out stringByAppendingString:album];
                  out = [out stringByAppendingString:@"\",\"Artist\" : \""];
                  out = [out stringByAppendingString:artist];
                  out = [out stringByAppendingString:@"\"}\n"];
                  returnString = out;
                  //return CreateJavaStringFromNSString(env, out);
                  //fprintf(stdout, "%s", [out UTF8String]);
                  stop = YES;
              });
              [[NSRunLoop currentRunLoop] runMode:NSDefaultRunLoopMode beforeDate:[NSDate date]];
          }

          return CreateJavaStringFromNSString(env, returnString);
    }

  }