# Iso Fluent

Helper functions for FluentCan `.apply`

## xo

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
.apply(~iso.xo("oxxxo")) // every five notes, first and last notes (denoted by "o") are rests, "x" sound. Index numbers, for each voices may be used as well: "o10xo"
.repeat(inf)
.play
)

a.canon.canon.collect(_.notes) // the arrays of notes for each voice of the canon
```