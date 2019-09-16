# Iso Fluent

Helper functions for FluentCan `.apply`

This repository is a work in progress. 

## Installation
## Using git (recommended)
If you have `git` installed (highly recommended, https://git-scm.com/downloads), the easiest way to install this software is by compiling this lines in the SuperCollider IDE:

```supercollider
Quarks.install("wslib"); // this lib is necessary for the correct functioning of IsoFluent
Quarks.install("https://github.com/nanc-in-a-can/iso-fluent.git");
```

Then you need to recompile the class library. In the menu bar: `Language > Recompile Class Library`


### Manual download
Otherwise you can follow this guide to find the paths in which you may install this software: http://doc.sccode.org/Guides/UsingExtensions.html

For that you will need to download this repository.
[Click here](https://github.com/nanc-in-a-can/fluent-can/archive/master.zip) and save the zip file wherever you want.

If you do not have `nanc-in-a-can/canon-generator` installed, you will have to the same with [this repo](https://github.com/nanc-in-a-can/canon-generator/archive/master.zip) .


## xo
Allows for an isorhythmic selection of notes that will play or not in each voice. 

It receives an string of `"x"`, `"o"` and numbres from `0 - 9`. 
`"x"` means all voices at index sound.
`"o"` means all voices at index rest.
`number` means voice at index sound all others rest.


```supercollider
(
Can.init;
s.boot;
)

(
~iso = IsoFluent;
a = FluentCan(\can2)
.notes([61,62,63,64])
.period(10)
.tempos([1,2])
.len(20)
.apply(~iso.xo("oxxxo")) // every five notes, first and last notes are rests, inner three notes sound. Index numbers, for each voices may be used as well: "o10xo"
.repeat(inf)
.play
)

a.canon.canon.collect(_.notes) // the arrays of notes for each voice of the canon
```
