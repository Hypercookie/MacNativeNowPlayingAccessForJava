//clang -x objective-c -framework Foundation -framework MediaRemote -F /System/Library/PrivateFrameworks MediaRemoteEvents.m -o CurrentPlaying

#import <Foundation/Foundation.h>
#import "MediaRemote.h"

int main(int argc, const char *argv[]) {
    @autoreleasepool {
        __block BOOL stop = NO;
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
                fprintf(stdout, "%s", [out UTF8String]);
                stop = YES;
            });
            [[NSRunLoop currentRunLoop] runMode:NSDefaultRunLoopMode beforeDate:[NSDate date]];
        }
    }

    return 0;
}