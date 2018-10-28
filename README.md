# FILEHIDER

![screenshot](https://github.com/0x416c616e/filehider/blob/master/FileHider.png)

## READ THIS BEFORE USING

## QUICK START
If you just want to use the program and aren't interested in looking at the source code or compiling it yourself, just run the JAR file (FileHider.jar).

This is a single-class JavaFX program. MainFX is the entry point.

## This program has 2 key features:
1. It can hide files within other files (currently limited to image files for the "host" file).
2. It can retrieve files from other files.

There is also an about button which displays basic info about the program,
and there is also a link to the creator's website.

In order to hide an image, you need to type the filename in the host file field.
I might convert to a "FileChooser" object at some point, but this is simple for now.
Easier for the programmer, harder for the user. Oh well.

You also need to put in the image name of the file you want to hide within the other file.
That file will be base64-encoded and then appended to the end of the host file.

The hidden file can be any type of file, but the host file should really only be something
like a jpg, png, or gif. You can try with other formats, but it might corrupt it.

The thing about image formats is that you can add arbitrary data after the end of the actual image,
and it won't mess with the parsing/rendering of the image. It will just be like padding, that 
doesn't really affect anything, except in cases like this.

For the "hiding" section, both the host file and file to hide need to exist, or else it won't run.

For the "retrieving" section, the host file needs to exist, and it also needs to have a base64-encoded file at the end.

The file name of a file to retrieve to doesn't need to exist.

Remember that, if you only want to put a file name, and no path, that this will only work with files in the 
same directory as the File Hider software itself. If you want to go up a level, use .. and then use /
to go down into a different directory. If you don't know much about relative and absolute file paths, 
that's not good, but this readme isn't going to give an entire lesson on how to do that.

If you hide multiple files within one image file, it will only show the last one. Maybe later I can
add support for having multiple files within one file, but right now, it will only get the last line of the file
for decoding from base64 into whatever file type you specify.

### Shortcomings of this:
No support for multiple hidden files within a single file
Does not check if another file is hidden within it before appending another one (you can do this manually with a text or hex editor if you really want)
#### PLEASE NOTE:
The "retrieving" functionality does not delete whatever file is hidden in the host file.

This is mainly just a proof of concept, but there are probably better tools out there. 
It's just a test with steganography. Keep in mind that base64 is not encryption, and steganography
is not encryption either. Putting a file within another file isn't really secure.
You could optionally encrypt stuff, but that's kind of overkill for a project like this.

This is a test project relating to file IO, GUI stuff, and also manipulating files.

This is a test that can be used to see if a website has file upload issues. For example, not 
properly validating or vetting image uploads, like if a site lets you upload an image file.

I plan on extrapolating more on this concept in an upcoming project which will hopefully be able
to upload a web shell to a site by putting it into an image file, and also injecting PHP code
into the exif headers of image files.

This is just a way to mess around and get more experience with interesting concepts, but I don't
expect anyone to seriously use it for anything, considering the issues I mentioned previously.

I could make this project more fully-featured, but it would take a while and I think I'd 
rather move on to other projects.


