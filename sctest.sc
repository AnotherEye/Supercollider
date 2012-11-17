s = Server.local.boot;
s.quit;
Help.gui;

(
	SynthDef(\phaser, { arg out=0, in=0; 

    var input,dsig, mixed;
    input = SoundIn.ar(in, 1);
   
   dsig = AllpassL.ar(input, 4, SinOsc.ar(2, 0, 0.005, 0.005), 0);
		    mixed = input + dsig;
		    Out.ar([out, out+1], mixed);
			}).load(s);
)

a = Synth(\phaser, addAction:\addToTail)

{Vocoder.ar(Saw.ar(400,4),AudioIn.ar(1),56)}.play;
{Splay.ar(Vocoder.ar(Saw.ar(100,4),AudioIn.ar(1),56), 1, 0.99, 0)}.play;

{Splay.ar(Vocoder.ar(Synth(\default, [\freq, 160, \amp, 0.6]),AudioIn.ar(1),56), 1, 0.5, 0)}.play;
Synth(\default, [\freq, 160, \amp, 0.6]);

(
var notes, on, off;

//MIDIIn.connect;

	b = MyThing.new();
	b.initMidi;
notes = Array.newClear(128);  // array has one slot per possible MIDI note

on = NoteOnResponder({ |src, chan, num, veloc|
	notes[num] = Synth(\default, [\freq, num.midicps, \amp, veloc * 0.00315]); });

off = NoteOffResponder({ |src, chan, num, veloc| notes[num].release; });

q = { on.remove; off.remove; };
)

b.initMidi;
b.killMidi;
