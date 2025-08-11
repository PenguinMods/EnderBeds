# Ender Beds!

Inspired by [this Reddit thread](https://redd.it/1m1s3p9), I present to you:\
**Ender Beds!**

<img alt="The mod's icon" src="https://cdn.modrinth.com/data/cached_images/a86eae12528c9feaa4568d676b1cc83c127c821a.png" width="30%">

[Get it on GitHub](https://github.com/PenguinMods/EnderBeds/releases)\
[Get it on Modrinth](https://modrinth.com/mod/ender_beds)

> [!NOTE]
> I'm very well aware that the textures in this mod are quite basic - I am no texture artist.
> If you *are*, and would like to submit your own textures, please don't hesitate to contribute!
> 
> The preferred way to do this is to [create a Pull Request](https://github.com/PenguinMods/EnderBeds/compare) or to [start a discussion](https://github.com/PenguinMods/EnderBeds/discussions/new/choose) on the GitHub repository. Alternatively, get in touch at mods+ender_beds@blockypenguin.com.\
> Thanks! :D

## What does the mod add?

### Echo Dust
<img src="https://cdn.modrinth.com/data/truuiqI6/images/de8ba99481a834c07b0a5dc21edcff315732919c.png" alt="Echo Dust's in-game texture" style="image-rendering: pixelated" width="10%">

You craft two of these from one Echo Shard. They are used as an ingredient of Ender Fabric.

### Ender Fabric
<img src="https://cdn.modrinth.com/data/truuiqI6/images/cf0ca9a9aae9e6f3313a8a9e5efe4e68c89f6377.png" alt="Ender Fabric's in-game texture" style="image-rendering: pixelated" width="10%">

You craft three of these from two Echo Dust, one Eye of Ender, and one Black Wool. They are used as an ingredient of Ender Beds.

### Ender Beds
<img src="https://cdn.modrinth.com/data/truuiqI6/images/a86eae12528c9feaa4568d676b1cc83c127c821a.png" alt="A pixelated rendering of an Ender Bed, as it appears in a player's inventory." style="image-rendering: pixelated" width="10%">

You craft one of these as you would a regular bed, using Ender Fabric instead of wool.

### Gamerules
The mod also adds a new gamerule, `enderBedWakeBehaviour`, which can be set to the following values:
* `DO_NOTHING` - This is the default behaviour. When an Ender Bed is slept in, you will wake up at your spawn point and nothing else will happen.
* `DESTROY_BED` - This will destroy the Ender Bed when you wake up. It will not drop an item.
* `RETURN_ITEM_TO_PLAYER` - This will break the Ender Bed and give it back to the player when they wake.

## What does the mod do?

When you sleep in an Ender Bed, your spawn is not set, and when you wake up you find yourself in the last bed you slept in (or your previous spawnpoint). Like when respawning, if you have no spawnpoint set or it is obstructed, you will be taken to the world spawn.
