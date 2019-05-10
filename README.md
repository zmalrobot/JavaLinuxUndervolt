# JavaLinuxUndervolt

Before starting: 
>This was made for my personal usage.<br>
>It's only tested on my pc and on my os ( see specs below )<br>
>PLEASE, be carefull!<br>

## What is it?

This is a small gui created in Java for the undervolt.py scritpt written by georgewhewell [here](https://github.com/georgewhewell/undervolt)

## What can i do? 

You can set each value core, gpu, cache, uncore, analogio (temperature target on my pc does not work so I haven't implemented it). 
You can also save your profile.

## How to use

If you are on a debian base system:

```
1) Download the last build from [HERE](https://github.com/zmalrobot/JavaLinuxUndervolt/tree/master/build) 
2) Unzip the file 
3) If it is your first time using it launch install.sh ( see below for more info... or open it! )
3) Wait until it finishes
4) To open the gui launch run.sh ( or just type sudo java -jar DownvoltLinuxUi.jar <-- sudo is required for the script )
5) Have fun ;-)

```

Else

```
1) Download the last build from [HERE](https://github.com/zmalrobot/JavaLinuxUndervolt/tree/master/build) 
2) Install python3 
3) Install java (It has been compiled to work on Java 11)
4) Download all the file from here: https://github.com/georgewhewell/undervolt
5) Inside the "resource" folder create a folder named "externaltools" (see the config for more info)
6) Put the downloaded file in the new folder you just created in the step above, do not rename it
7) Check if all the scripts are compatible with your system, if not change it
8) Run with sudo privileges

Mabye

9) If it doesn't work change the command in the code and rebuild it... sorry I'll try to resolve this as soon as possible

```

## How it works

1) When you click apply it runs the script inside "externaltools" folder with your params
2) Then it will create a 10 second  full load on all your cpu core to test the settings of your choice
3) See FAQ inside the gui for more info!


## Future release

I'm currently workin on:

- Real time cpu frequency monitor
- Real time cpu load monitor
- Real time temperature monitor
- Cpu info
- Stress test / benchmark


Tested on: 

```
Asus fx504GE
cpu: i7 8750h
gpu: UHD Graphics 630 + gtx1050Ti
ram: 16Gb ddr4
Os: Ubuntu 19.04 + Ubuntu 18.04

```

## Other info

install.sh

```
IT WILL ONLY WORK ON UBUNTU ( or mabye some debian distro )

it will install:

1) openjdk
2) python3

Then it will download the script from here: https://github.com/georgewhewell/undervolt

Ah and also check if you are running an intel base system or not ;-)

```

## Thanks to

[georgewhewell](https://github.com/georgewhewell/undervolt)
<br>
[Caffinc](https://caffinc.github.io/2016/03/cpu-load-generator/)


