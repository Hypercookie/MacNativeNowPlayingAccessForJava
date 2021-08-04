#import <Foundation/Foundation.h>
#import "MediaRemote.h"
#import "objcPleaseDontKillMe_JNITest.h"
#import "ConversionUtils.h"

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


JNIEXPORT jstring JNICALL Java_objcPleaseDontKillMe_JNITest_getCurrentSong
  (JNIEnv *env, jobject obj)
  {
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