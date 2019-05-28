+ IsoFluent {
    *prXoAddDurs {|durs, e|
        ^if((e.asString != "o"),
            {durs.add(1);},
            {
                if((durs.size == 0) || (durs.last == \rest),
                    {durs.add(\rest);},
                    {durs[durs.size -1] = durs.last+1;}
                );
                durs;
            }
        )
    }

    *prXoInitVoiceNotes {|totalVoices|
        ^totalVoices.collect({[]})
    }

    *prXoAddVoiceNotesEvent {|voices, e|
        ^case(
            {e.asString == "x"}, {voices.collect(_.add(1))},
            {e.asString == "o"}, {voices.collect(_.add(\rest))},
            {e.asString.asNumberIfPossible.isNumber}, {
                var index = e.asString.asNumberIfPossible;
                voices.collect({|voice, i| if(i == index,
                    {voice.add(1)},
                    {voice.add(\rest)})
                })
            },
            {
                voices.collect(_.size).every(_ == 0)
                || voices.flatten.every(_ == \rest)
            }, {voices.collect(_.add(\rest))},
            {voices}
        )
    }

    *prXoMakeTranspFns {|mappingsNotes|
        ^mappingsNotes.collect({|voice| _*voice})
    }

    *prXoMakeMappings {|soundstring, totalVoices|
        var acc = (
            durs: List [],
            notes: IsoFluent.prXoInitVoiceNotes(totalVoices) // actually, this builds a list of 1 || \rest which will be used as transp fn's later on
        );
        ^soundstring.inject(acc, {|acc, e|
            var durs = acc.durs;
            var notesVoices = acc.notes;
            (
                durs: IsoFluent.prXoAddDurs(durs, e),
                notes: IsoFluent.prXoAddVoiceNotesEvent(notesVoices, e)
            );
        });
    }

    *xo {|xos|
        ^{|fluentCan, def|
            var mappings = IsoFluent.prXoMakeMappings(xos, fluentCan.tempos.size);
            var transps = IsoFluent.prXoMakeTranspFns(mappings.notes);
            var maybeNewCan = if(def.isNil.not, {fluentCan.copy.def(def ? fluentCan.def).debug("new")}, {fluentCan});
            maybeNewCan.transps(transps);
            maybeNewCan;
        };
    }
}