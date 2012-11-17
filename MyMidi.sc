s = Server.local.boot;
s.quit;
Help.gui;
SCVim.generateTagsFile();
Platform.helpDir
Platform.userExtensionDir;   // Extensions available only to your user account
Platform.systemExtensionDir; // Extensions available to all users on the machine

a = ("abc" ++ 10.asString()).asSymbol.class
a
a.free
a = {SinOsc.ar(440, 0, 0.3)}.scope ;
a = {SinOsc.ar(MouseX.kr(20,200), 0, 0.4)}.play;
a = {SinOsc.ar(440, 0, 0.3)}.play ;

b = MyThing.new();
b.initMidi;
b.initKnob(10);
b.initKnob(11);
b.killMidi;
MIDIIn.noteOn = nil;
MIDIIn.disconnect
(
	MIDIIn.connect; 	// init for one port midi interface
	// register functions:	
	MIDIIn.noteOff = { arg src, chan, num, vel;		"NoteOff ".post; [chan,num,vel / 127].postln; };
	MIDIIn.noteOn = { arg src, chan, num, vel; 		"NoteOn  ".post; [chan,num,vel / 127].postln; };
	MIDIIn.polytouch = { arg src, chan, num, vel; 	"PolyTch ".post; [chan,num,vel / 127].postln; };
	MIDIIn.control = { arg src, chan, num, val; 	"Control ".post; [chan,num,val].postln; };
	MIDIIn.program = { arg src, chan, prog; 		"Program ".post; [chan,prog].postln; };
	MIDIIn.touch = { arg src, chan, pressure;		"Touch   ".post; [chan,pressure].postln; };
	MIDIIn.bend = { arg src, chan, bend; 			"Bend    ".post; [chan,bend - 8192].postln; };
	MIDIIn.sysex = { arg src, sysex; 				"Sysex   ".post; sysex.postln; };
	MIDIIn.sysrt = { arg src, chan, val; 			"Sysrt   ".post; [chan,val].postln; };
	MIDIIn.smpte = { arg src, chan, val; 			"SMPTE   ".post; [chan,val].postln; };
)

MIDIIn.noteOn = nil;
MIDIIn.noteOff = nil;
MIDIIn.sysrt = nil;
MIDIIn.control = nil;
MIDIIn.disconnect(0);

(
	MIDIIn.connect(0, MIDIClient.sources.at(3));
	MIDIdef.cc(\test3, {arg ...args; args.postln}, (1..10)); // match cc 1-10
)
MIDIIn.disconnect(0, MIDIClient.sources.at(3));
(
	MIDIdef.cc(\knob1, 
		{
			arg val, num, chan, src;
			[val, num, chan, src].postln;
		},
		10,
		0);

)
MIDIdef.freeAll;
	
(
var conMidi;
conMidi = {
	var ox8Port, ox8Uid;
	ox8Port = MIDIIn.findPort("Keystation-Keystation MIDI 1","Keystation-Keystation MIDI 1");
	ox8Uid = ox8Port.uid;
	MIDIIn.connect(0, ox8Port);
};
)
MIDIClient.init;
MIDIClient.sources;
MIDIIn.connect(0, MIDIIn.findPort("Keystation-Keystation MIDI 1","Keystation-Keystation MIDI 1"));
MIDIIn.disconnect(0, MIDIIn.findPort("Keystation-Keystation MIDI 1","Keystation-Keystation MIDI 1"));
MIDIIn.findPort("Keystation-Keystation MIDI 1","Keystation-Keystation MIDI 1").uid;
MIDIIn.class.dumpAllMethods
MIDIEndPoint.class.dumpAllMethods

(
MIDIIn.connect;
MIDIIn.noteOn = { |port, chan, note, vel| [port, chan, note, vel].postln };
MIDIIn.noteOn = nil;    // stop responding
)

\ll.asString
(
	
	var knob1;

	w = Window.new("myWindow", Rect(1000,500, 300, 300)).front;
//	w.onClose_( { mySynth.free });

	knob1 = Knob.new(w, Rect(30,30,80,80));
	knob1.action_(
		{
			MIDIIn.doControlAction(1310720, 0, 10, (knob1.value*127.999).floor)
		});
	MIDIdef.cc(\knob1, 
		{
			arg val, num, chan, src;
			knob1.value = val/128;
			[val, num, chan, src].postln;
		},
		10,
		0);
)
