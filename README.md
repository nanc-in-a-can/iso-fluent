# Iso Fluent

Helper functions for FluentCan `.apply`

This repository is a work in progress. 

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