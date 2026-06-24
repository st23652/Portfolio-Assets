from pytube import YouTube

def download(link):
    ytlink = YouTube(link)
    ytlink = ytlink.streams.get_highest_resolution()

    try:
        ytlink.download()
    except:
        print("there has been an error")
    print("download is done")

link = input("youtube video url: ")
Download(link)
