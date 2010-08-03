//   Copyright 2010 Arnold B. Spence
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.

package net.psilent.psilentjacks;

import java.applet.Applet;
import java.applet.AudioClip;

public class SoundFX
{
    public enum Sound
    {
        SHUFFLE, CARD_FLIP, WIN, LOSE
    }

    public static void setMuted(boolean muted)
    {
        SoundFX.muted = muted;
    }

    public static void play(Sound sound)
    {
        soundShuffle.stop();
        soundCardFlip.stop();
        soundLose.stop();
        soundWin.stop();
        
        if(SoundFX.muted)
            return;

        if(sound == Sound.SHUFFLE)
        {
            SoundFX.soundShuffle.play();
        }
        else if(sound == Sound.CARD_FLIP)
        {
            SoundFX.soundCardFlip.play();
        }
        else if(sound == Sound.WIN)
        {
            SoundFX.soundWin.play();
        }
        else
        {
            SoundFX.soundLose.play();
        }
    }

    private static boolean muted = false;
    private static AudioClip soundShuffle = Applet.newAudioClip(SoundFX.class.getResource("/net/psilent/psilentjacks/resources/sounds/deck_shuffle.wav"));
    private static AudioClip soundCardFlip = Applet.newAudioClip(SoundFX.class.getResource("/net/psilent/psilentjacks/resources/sounds/card_flip.wav"));
    private static AudioClip soundWin = Applet.newAudioClip(SoundFX.class.getResource("/net/psilent/psilentjacks/resources/sounds/win.wav"));
    private static AudioClip soundLose = Applet.newAudioClip(SoundFX.class.getResource("/net/psilent/psilentjacks/resources/sounds/lose.wav"));
}
