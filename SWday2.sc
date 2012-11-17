upstate soundsceape

s = Server.local.boot;
s.quit;
Help.gui;

//written as a function
{SinOsc.ar(440,0,0.5)}.play;

//written as a synth definition
SynthDef.new("sine-wave-def",{Out.ar(0, SinOsc.ar(440,0,0.5))}).play;

// send it to the server
SynthDef.new("sine-wave-def",{Out.ar(0, SinOsc.ar(440,0,0.5))}).send(s);

// send it to the server and make it persistant (in a file somewhere
SynthDef.new("sine-wave-def",{Out.ar(0, SinOsc.ar(440,0,0.5))}).load(s);

// use the stored SynthDef
a = Synth.new("sine-wave-def").play
b = Synth.new("sine-wave-def").play
a.free;
b.free;


//to the left
SynthDef.new("sine-wave",{Out.ar(0, SinOsc.ar(440,0,0.5))}).play;

//to the right
SynthDef.new("sine-wave",{Out.ar(1, SinOsc.ar(440,0,0.5))}).play;

//split
SynthDef.new("sine-array",{ Out.ar(0, [ SinOsc.ar(440, 0, 0.2), SinOsc.ar(642, 0, 0.2)])}).play;


// lets play

// using args
SynthDef.new("sine-wave-def",
	{
		arg freq = 400, out = 0;
		Out.ar(out, SinOsc.ar(freq,0,0.5))
	}).send(s);


a = Synth.new("sine-wave-def", ["freq", 500, "out", 1]).play
a = Synth.new("sine-wave-def", ["out", 1, "freq", 600]).play
b = Synth.new("sine-wave-def").play
a.free;
b.free;

// Exercises
// exercise 1
SynthDef.new("ex1", { Out.ar(0, SinOsc.ar(329, 0, 0.5))}).play
// exercise 2
SynthDef.new("ex1", 
	{
		arg freq = 329, out = 0;
		Out.ar(out, SinOsc.ar(freq, 0, 0.5))
	}).send(s);
a = Synth.new("ex1").play;
b = Synth.new("ex1", ["freq", 440, "out", 1]).play;
a.free;
b.free;
b = Synth.new("ex1",  440,  1).play;


(
	var synth1;

	synth1 = SynthDef ("variable-freq-sine",
		{
			arg freq = 440;
			Out.ar(0, SinOsc.ar(freq,0,0.5))
		}).play(s); 
	
	w =  Window.new("gui-sine-test", Rect(1000,200,300,300));
	w.front;
	w.addToOnClose ( { synth1.free });

	k = Knob.new(w,  Rect(30,30, 50,50));
	z = StaticText(w, Rect(30, 90, 50, 20));
	z.string = "Frequency";

	k.value = 440/880;
	k.color[0] = Color.black;
	k.color[1] = Color.red;
	k.color[2] = Color.green;
	k.color[3] = Color.blue.alpha_(0.5);
	k.action_(
		{
			synth1.set( \freq, k.value*880)
		});
)

(

	var mySynth, freqKnob, outKnob;

	mySynth = SynthDef("mySynth",
		{
			arg freq = 220, out = 0;
			Out.ar(out, SinOsc.ar(freq, 0, 0.5))
		}).play(s);

	w = Window.new("myWindow", Rect(1000,500, 300, 300)).front;
	w.onClose_( { mySynth.free });

	freqKnob = Knob.new(w, Rect(30,30,50,50));
	freqKnob.action_(
		{
			mySynth.set(\freq, freqKnob.value*440);
		});

	outKnob = Knob.new(w, Rect(100,30,50,50));
	outKnob.action_(
		{
			mySynth.set(\out, outKnob.value*2);
		});

)
2.postln
s.record("\/home\/ghampton\/Supercollider\/blah");
s.stopRecording;

w.close;
